package somaMVP.domain.gps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GpsCrudService {
    private final UserGpsRepository userGpsRepository;
    public String createGps(String id) {
        UserGps userGps = new UserGps(id);
        userGpsRepository.save(userGps);
        return userGps.getId();
    }
    public String updateGps(String id, UserGpsDto userGpsDto) {
        UserGps userGps = userGpsDto.toEntity();
        Optional<UserGps> byId = userGpsRepository.findById(id);
        if (byId.isPresent()) {
            userGpsRepository.save(userGps);
            return userGps.getId();
        } else {
            return "not found";
        }
    }
}
