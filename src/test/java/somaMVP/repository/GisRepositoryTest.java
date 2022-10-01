package somaMVP.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import somaMVP.domain.gis.Gis;
import somaMVP.domain.gis.GisRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GisRepositoryTest {
    @Autowired
    GisRepository gisRepository;
    @Transactional
    @Test
    void testGis() {
        Gis gis = new Gis();
        //member.setUserName("test");
        Long savedId = gisRepository.save(gis);
        Gis findGis = gisRepository.findById(savedId);
        assertThat(findGis.getId()).isEqualTo(savedId);
        assertThat(findGis.getAcousticExist()).isEqualTo(gis.getAcousticExist());
        assertThat(findGis).isEqualTo(gis);
    }

}