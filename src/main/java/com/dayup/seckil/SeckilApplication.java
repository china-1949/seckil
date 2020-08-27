package com.dayup.seckil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication

//下面的优化springboot的加载：只要引入Positive matches下面的类   Negative matches 不要匹配  只能启动更快
//@Configuration
//@Import({
	
//})
public class SeckilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckilApplication.class, args);
	}

}
