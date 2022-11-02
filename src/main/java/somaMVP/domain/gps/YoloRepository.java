package somaMVP.domain.gps;

import org.springframework.data.repository.CrudRepository;
import somaMVP.domain.file.ObjectDto;


public interface YoloRepository extends CrudRepository<ObjectDto, String> {

}