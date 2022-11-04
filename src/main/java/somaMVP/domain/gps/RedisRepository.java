package somaMVP.domain.gps;

import org.springframework.data.repository.CrudRepository;


public interface RedisRepository extends CrudRepository<String, String> {

}