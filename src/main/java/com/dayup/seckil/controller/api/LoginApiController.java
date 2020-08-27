package com.dayup.seckil.controller.api;




import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dayup.seckil.VO.UserVo;
import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.base.result.ResultCode;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Utils;
import com.dayup.seckil.util.UUIDUtil;





@RestController
public class LoginApiController extends BaseApiController {
	private static Logger log =LoggerFactory.getLogger(LoginApiController.class);
	
	@Autowired 
	public UserService userService;
//	
//	@RequestMapping(value="/reg", method=RequestMethod.GET)
//	public String toRegister(Model model){
//		model.addAttribute("user",new User());
//		return "regiter";
//	}
	
	
	
// 	@ResponseBody  或者在controller使用@RestController
//没有指定get请求或者post请求，说明2者都是可以的
	@RequestMapping(value="/login")
	public Result<Object> Register(@ModelAttribute(value="user") @Valid User user ,BindingResult bindingResult ,HttpSession session 
			,Model model ,HttpServletResponse  response){
		
		log.info("username="+user.getUsername()+",password="+user.getPassword()+",id="+user.getId() );
		if(bindingResult.hasErrors()){//校验用户名和密码不为空
			return Result.failure();  //500 ， error
		}
//		
//		String sessionCode = (String) session.getAttribute("code");
//		if(!StringUtils.equalsIgnoreCase(code, sessionCode)){
//			//向页面回传信息
//			model.addAttribute("message","验证码不匹配！！");
//			return "login";
//		}
		UserVo dbUser = userService.getUser(user.getUsername());
		if(dbUser !=null){
			if(dbUser.getPassword().equals(MD5Utils.inputToDb(user.getPassword(), dbUser.getDbflag()))){
			//	session.setAttribute("user", dbUser);
				
				String token = UUIDUtil.getUUID();
				userService.saveUserToRedisByToken(dbUser,token);
				
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(3600);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				return Result.success();  // Result.success();  200 "sucess"   这里可以 Result.sucess(dbUser) 则返回为Result<UserVo>
			}else{
				return Result.failure(ResultCode.USER_LOGIN_ERROR);
			}
		}else{
			return Result.failure(ResultCode.USER_LOGIN_ERROR);
		}
		
	}
	
	
}
