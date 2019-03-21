package com.afeng.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //开启MockMvc
public class ApiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc; //注入MockMvc

    @Test
    public void testHelloController() throws Exception {

        mockMvc.perform(get("/api/v1/index/hello")) //请求方式+地址
                .andDo(print()) //打印效果
                .andExpect(status().isOk()) //预期状态
                .andExpect(content().string(containsString("hahaha")));
    }
}
