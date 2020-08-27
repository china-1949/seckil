package com.dayup.seckil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApiControllerTest {
	
	private MockMvc mockMvc;
	//引入context
	@Autowired
	private WebApplicationContext context;
	
	@org.junit.Before
	public void setup(){
		this.mockMvc =MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void contextLoads() throws Exception{
		MvcResult result = mockMvc.perform(post("/api/login").param("username", "chensong").param("password", "123456")
				.contentType(MediaType.APPLICATION_JSON)) //指定返回json格式
				.andExpect(status().isOk()) //是否成功返回
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) //检验返回是否为json格式的
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}










