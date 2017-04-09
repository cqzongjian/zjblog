package site.zongjian.zjblog.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.zongjian.zjblog.service.UserService;
import site.zongjian.zjblog.util.Result;

import javax.annotation.Resource;

@Controller//ɨ��
@RequestMapping("/user")
public class UserLoginController {
	@Resource//ע��
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Result execute(String name,String password){
		Result result = userService.checkLogin(name, password);
		return result;
	}
	
}
