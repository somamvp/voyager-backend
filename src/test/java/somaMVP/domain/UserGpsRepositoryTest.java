//package somaMVP.domain;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.connection.stream.StreamOffset;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StreamOperations;
//import somaMVP.domain.gps.UserGps;
//import somaMVP.domain.gps.UserGpsRepository;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//class UserGpsRepositoryTest {
//
//    @Autowired
//    private UserGpsRepository repo;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Test
//    void test() {
//        UserGps userGps = new UserGps("test");
//        // 저장
//        repo.save(userGps);
//        // `keyspace:id` 값을 가져옴
//        Optional<UserGps> findGps = repo.findById(userGps.getId());
//        findGps.ifPresent(gps -> {
//            assertThat(gps.getGpsX()).isEqualTo(userGps.getGpsX());
//            assertThat(gps.getGpsY()).isEqualTo(userGps.getGpsY());
//        });
//        log.info("keyspace:id : {}", findGps.get().getId());
//        log.info("keyspace:id : {}", findGps.get().getGpsY());
//        // Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
//        log.info("user 키 갯수 : {}", repo.count());
//
//        // 삭제
////        repo.delete(userGps);
//    }
//
//    @Test
//    void testHash() {
//        String key = "test";
//        // given
//        UserGps userGps = new UserGps("testId");
//        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
//
//        // when
//        hashOperations.put(key, "gpsX", String.valueOf(userGps.getGpsX()));
//        hashOperations.put(key, "gpsY", String.valueOf(userGps.getGpsY()));
//
//        // then
//        Object value = hashOperations.get(key, "gpsX");
//        assertThat(value).isEqualTo("0.0");
//
//        Map<Object, Object> entries = hashOperations.entries(key);
//        assertThat(entries.keySet()).containsExactly("gpsX", "gpsY");
//        assertThat(entries.values()).containsExactly("0.0", "0.0");
//
//        Long size = hashOperations.size(key);
//        assertThat(size).isEqualTo(entries.size());
//    }
//    @Test
//    void testStream() {
//        String key = "testStream";
//        String id = "1345235";
//        Map<String, String> testMap = new HashMap<>();
//        testMap.put("userId", id);
//        testMap.put("gpsX", "0.0");
//        testMap.put("gpsY", "0.0");
//        // given
//        UserGps userGps = new UserGps("test");
//        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
//        // when
//        streamOperations.add(key, testMap);
//        StreamOffset<String> streamOffset = StreamOffset.fromStart(key);
//        // then
//        streamOperations.read(streamOffset).forEach(record -> {
//            assertThat(record.getValue().get("userId")).isEqualTo(id);
//            assertThat(record.getValue().get("gpsX")).isEqualTo("0.0");
//            assertThat(record.getValue().get("gpsY")).isEqualTo("0.0");
////            streamOperations.delete(key, record.getId()); // 전체 지워 버림 주의
//        });
////        streamOperations.trim(key, 2);  2개만 남김. //TODO : 쿠키, 서비스 로직 짜기
//    }
//}