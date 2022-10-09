//package somaMVP.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.io.FileInputStream;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@DisplayName("FileUploadController Test")
//@Slf4j
//class FileUploadControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @Value("${uploadDir}")
//    private String uploadDir;
//
//    @DisplayName("mlUploadFile Test")
//    @Test
//    void mlUploadFile() throws Exception {
//        //given
//        final String fileName = "receive"; //파일명
//        final String contentType = "webp"; //파일타입
//        FileInputStream fileInputStream = new FileInputStream(uploadDir + "webpsample.webp");
//
//        //Mock파일생성
//        MockMultipartFile mockImage = new MockMultipartFile(
//                "source", //name
//                fileName + "." + contentType, //originalFilename
//                contentType,
//                fileInputStream
//        );
//        //when & then
//        MvcResult mvcResult = mvc.perform(
//                        multipart("/api/v1/files/ml/upload")
//                                .file(mockImage)
//                        //.param("img2", "test")
//                ).andExpect(status().isOk())
//                .andReturn();
//        log.info(mvcResult.getResponse().getContentAsString());
//    }
////
////    @DisplayName("uploadFile Test")
////    @Test
////    void uploadFile() throws Exception {
////        //given
////        final String fileName = "receive"; //파일명
////        final String contentType = "webp"; //파일타입
////        FileInputStream fileInputStream = new FileInputStream(uploadDir + "webpsample.webp");
////
////        //Mock파일생성
////        MockMultipartFile mockImage = new MockMultipartFile(
////                "source", //name
////                fileName + "." + contentType, //originalFilename
////                contentType,
////                fileInputStream
////        );
////
////        //when & then
////        MvcResult mvcResult = mvc.perform(
////                        multipart("/upload")
////                                .file(mockImage)
////                        //.param("img2", "test")
////                ).andExpect(status().isOk())
////                .andReturn();
////        log.info(mvcResult.getResponse().getContentAsString());
////    }
//}