package site.zongjian.zjblog.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.zongjian.zjblog.service.UserService;
import site.zongjian.zjblog.util.Result;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public Result execute(
		String name,String password,String nick){
		Result result =
		userService.registUser(name, nick, password);
		return result;
	}
	
}
