//package somaMVP.domain.gps;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.util.StopWatch;
//import org.springframework.web.context.WebApplicationContext;
//import somaMVP.config.RestDocsConfiguration;
//import somaMVP.domain.gis.GisDto;
//import somaMVP.domain.gis.GisDtos;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@Slf4j
//@AutoConfigureRestDocs
//@Import(RestDocsConfiguration.class)
//class GpsServiceTest {
//    @Autowired
//    GpsService gpsService;
//    StopWatch stopWatch = new StopWatch();
//    @Autowired
//    protected RestDocumentationResultHandler restDocs;
//
//    @BeforeEach
//    void setUp() {
//        stopWatch.start();
//        final WebApplicationContext context;
//        final RestDocumentationContextProvider provider;
//    }
//    @AfterEach
//    void tearDown() {
//        stopWatch.stop();
//        log.info("총 수행 시간 => {} sec", stopWatch.getTotalTimeSeconds());
//    }
//
//    @Test
//    void nearZebra() {
//        GisDtos gisDtos = gpsService.nearZebra(127.119119699043,37.5337832826399, 0.3);
//        Integer findCount = gisDtos.getDataCount();
//        List<GisDto> data = gisDtos.getData();
//        for (GisDto gisDto : data) {
//            log.info("address: {}", gisDto.getAddress());
//            log.info("GPSY: {}", gisDto.getGpsY());
//            log.info("GPSX: {}", gisDto.getGpsX());
//        }
//        assertThat(findCount).isPositive();
//        data.forEach(gisDto -> {
//            assertThat(gisDto.getGpsX()).isNotNull();
//            assertThat(gisDto.getGpsY()).isNotNull();
//        });
//    }
//}