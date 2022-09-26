//package somaMVP.controller;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import somaMVP.response.UuidResponse;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(SwaggerController.class)
//@DisplayName("SwaggerController Test")
//@Slf4j
//class SwaggerControllerTest {
//    @Autowired private MockMvc mvc;
//    @Autowired private Gson gson;
//
//    @DisplayName("Hello Test")
//    @Test
//    void WebMvcTestHello_v2() throws Exception{
//        //given
//        String name = "juwon";
//        //when & then
//        mvc.perform(
//                get("/api/posts/hello?name=" + name)
//                        .contentType("application/json")
//                        .content("{\"name\":\"" + name + "\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello " + name));
//    }
//    @DisplayName("Uuid Test")
//    @Test
//    void UuidTest() throws Exception{
//        //given
//        Long uuid = 1234L;
//        String name = "juwon";
//        UuidResponse uuidResponse = new UuidResponse();
//        uuidResponse.setUuid(uuid);
//        uuidResponse.setName(name);
//
//        String json = gson.toJson(uuidResponse);
//        //when
//        mvc.perform(
//                post("/api/posts/test")
//                        .contentType("application/json")
//                        .content(json))
//                        .andExpect(status().isOk())
//                        .andExpect(content().string(json));
//        //then
//        log.info(json);
//    }
//}