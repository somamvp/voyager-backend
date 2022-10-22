// package somaMVP.domain.gps;

// import com.google.gson.Gson;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockCookie;
// import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.transaction.annotation.Transactional;
// import somaMVP.config.RestDocsConfiguration;
// import somaMVP.domain.gis.GisRepository;
// import somaMVP.domain.session.SessionConst;

// import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @Import(RestDocsConfiguration.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// @AutoConfigureRestDocs(uriPort = 8081)
// @ActiveProfiles("test")
// @DisplayName("GpsController Test")
// @Slf4j
// @Transactional(readOnly = true)
// class GpsControllerTest {
//     @Autowired
//     private MockMvc mockMvc;
//     @Autowired
//     protected RestDocumentationResultHandler restDocs;
//     @Autowired
//     private GpsService gpsService;
//     @Autowired
//     private GisRepository gisRepository;
//     public static String GPS_ID = "gpsId";

//     @Test
//     void createGps() throws Exception {
//         ResultActions resultActions = mockMvc.perform(
//                         get("/api/v1/gps/create")
//                                 .contentType(MediaType.APPLICATION_JSON)
//                 )
//                 .andExpect(status().isOk())
//                 .andDo(restDocs.document());
//         GPS_ID = resultActions.andReturn().getResponse().getContentAsString();
//     }
//     @Test
//     void updateGps() throws Exception {
//         MockCookie mockCookie = new MockCookie(SessionConst.GPS_ID, GPS_ID);
//         UserUpdateGpsDto updateDto = new UserUpdateGpsDto("37.5337832826399", "127.119119699043");
//         //given
//         mockMvc.perform(
//                         post("/api/v1/gps/update")
//                                 .cookie(mockCookie)
//                                 .content(new Gson().toJson(updateDto))
//                                 .contentType(MediaType.APPLICATION_JSON)
//                 )
//                 .andExpect(status().isOk())
//                 .andDo(restDocs.document());
//     }

//     @Test
//     void reportGps() throws Exception {
//         UserGpsDto userGpsDto = new UserGpsDto(37.5337832826399, 127.119119699043, 0.1);
//         mockMvc.perform(
//                         post("/api/v1/gps/report")
//                                 .content(new Gson().toJson(userGpsDto))
//                                 .contentType(MediaType.APPLICATION_JSON)
//                 )
//                 .andExpect(status().isOk())
//                 .andDo(restDocs.document());
//     }

//     @Test
//     void expire() throws Exception {
//         mockMvc.perform(
//                         get("/api/v1/gps/expire")
//                                 .contentType(MediaType.APPLICATION_JSON)
//                 )
//                 .andExpect(status().isOk())
//                 .andDo(restDocs.document());
//     }

//     @Test
//     void getDirection() {
//     }
// }