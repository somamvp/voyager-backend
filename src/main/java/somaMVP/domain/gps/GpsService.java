package somaMVP.domain.gps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.stereotype.Service;
import somaMVP.domain.gis.Gis;
import somaMVP.domain.gis.GisDto;
import somaMVP.domain.gis.GisDtos;
import somaMVP.domain.utils.CardinalDirection;
import somaMVP.domain.utils.GeometryUtils;
import somaMVP.domain.utils.Location;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GpsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserGpsRepository userGpsRepository;
    private final EntityManager entityManager;

    public String createGps(String gpsId) {
        UserGps userGps = new UserGps(gpsId);
        userGpsRepository.save(userGps);
        return userGpsRepository.save(userGps).getId();
    }

    public GisDtos nearZebra(Double gpsY, Double gpsX, Double distance){
        // 북동쪽 좌표 구하기
        Location northEast = GeometryUtils.calculateByDirection(gpsX, gpsY, distance, CardinalDirection.NORTHEAST
                .getBearing());
        Location southWest = GeometryUtils.calculateByDirection(gpsX, gpsY, distance, CardinalDirection.SOUTHWEST
                .getBearing());
        double x1 = northEast.getLongitude();
        double y1 = northEast.getLatitude();
        double x2 = southWest.getLongitude();
        double y2 = southWest.getLatitude();

        // native query 활용
        Query query = entityManager.createNativeQuery("" +
                        "SELECT r.id, r.zebra_id, r.geometry, r.gpsx, r.gpsy, r.address, r.light_exist, r.acoustic_exist, r.created_date \n" +
                        "FROM `local-db`.GIS AS r \n" +
                        "WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + String.format("'LINESTRING(%f %f, %f %f)')", x1, y1, x2, y2) + ", r.geometry)"
                , Gis.class);

        List<Gis> results = query.getResultList();
        List<GisDto> data = results.stream()
                .map(GisDto::of)
                .collect(Collectors.toList());
        return GisDtos.builder()
                .dataCount(data.size())
                .data(data)
                .build();
    }

    public String updateGps(String id, UserGpsDto userGpsDto) {
        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
        log.info("userGpsDto.toMap(id) = " + userGpsDto.toMap(id, userGpsDto.getGpsX().toString(), userGpsDto.getGpsY().toString()));
        streamOperations.add(id, userGpsDto.toMap(id, userGpsDto.getGpsX().toString(), userGpsDto.getGpsY().toString()));
        streamOperations.trim(id, 6);
        return id;
    }

    public String deleteGps(String id) {
        redisTemplate.expire(id, 100,  TimeUnit.MILLISECONDS);
        redisTemplate.expire("user:"+id, 100,  TimeUnit.MILLISECONDS);
        redisTemplate.opsForSet().remove("user", id);
        return id;
    }

    public String getDirection(String id) {
        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
        StreamOffset<String> streamOffset = StreamOffset.fromStart(id);
        List<MapRecord<String, Object, Object>> readRecord = streamOperations.read(streamOffset);

        if(readRecord.isEmpty()) {
            return "not found";
        }
        if(readRecord.size() < 6) {
            return "not enough data";
        }
        double prevX = 0.0;
        double prevY = 0.0;
        double nextX = 0.0;
        double nextY = 0.0;
        double diffX;
        double diffY;

        for(int i=0; i<readRecord.size(); i++){
            if(i < 3){
                prevX += Double.parseDouble(readRecord.get(i).getValue().get("gpsX").toString());
                prevY += Double.parseDouble(readRecord.get(i).getValue().get("gpsY").toString());
            }
            else{
                nextX += Double.parseDouble(readRecord.get(i).getValue().get("gpsX").toString());
                nextY += Double.parseDouble(readRecord.get(i).getValue().get("gpsY").toString());
            }
        }
        prevX /= 3;
        prevY /= 3;
        log.info("prevX = " + prevX);
        log.info("prevY = " + prevY);

        nextX /= 3;
        nextY /= 3;
        log.info("nextX = " + nextX);
        log.info("nextY = " + nextY);

        diffX = nextX - prevX;
        diffY = nextY - prevY;
        log.info("nowX = " + diffX);
        log.info("nowY = " + diffY);

        return String.valueOf(Math.atan2(diffY, diffX) * 180 / Math.PI);
    }
}
