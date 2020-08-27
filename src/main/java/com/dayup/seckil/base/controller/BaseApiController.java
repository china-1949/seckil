package com.dayup.seckil.base.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api")
//@CrossOrigin(origins="*",allowCredentials="true",value ={"192.168.56.102":8081}) //指示固定的ip支持跨域访问的
@CrossOrigin(origins="*",allowCredentials="true") //所有的ip都可以
public class BaseApiController {
		
}
