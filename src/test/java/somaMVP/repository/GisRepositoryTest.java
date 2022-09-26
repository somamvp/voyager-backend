package somaMVP.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import somaMVP.domain.Gis;
import somaMVP.domain.GisRepository;
import somaMVP.domain.Member;
import somaMVP.domain.MemberRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
//    @Test
//    public void BaseTimeEntity_등록 () {
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        gisRepository.save(Gis.builder()
//                        .acousticExist("Y")
//                        .password("1234")
//                        .userName("jojoldu")
//                        .address("서울시 서초구 서초동")
//                        .disabledNumber("010-1234-5678")
//                        .email("jojoldu@gmail.com")
//                        .phoneNumber("010-1234-5678")
//                        .build());
//        //when
//        List<Gis> postsList = gisRepository.findAll();
//
//        //then
//        Gis gis = postsList.get(0);
//        assertTrue(gis.getCreatedDate().isAfter(now));
//        assertTrue(gis.getModifiedDate().isAfter(now));
//    }

}