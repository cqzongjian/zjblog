package site.zongjian.zjblog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.zongjian.zjblog.dao.UserDao;
import site.zongjian.zjblog.entity.User;
import site.zongjian.zjblog.service.UserService;
import site.zongjian.zjblog.util.Result;
import site.zongjian.zjblog.util.BlogUtil;

import javax.annotation.Resource;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource//注入
	private UserDao userDao;
	
	public Result checkLogin(String name, String password) {
		Result result = new Result();
		User user = userDao.findByName(name);
		//检测用户名
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户名错误");
//			result.setData(null);
			return result;
		}
		//检测密码
		//将用户输入的明文加密
		String md5Password = BlogUtil.md5(password);
		//比对数据库中密码
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码正确
		result.setStatus(0);
		result.setMsg("登录成功");
		//将用户身份ID返回
		result.setData(user.getCn_user_id());
		return result;
	}


	public Result registUser(
		String name, String nick, String password) {
		
		Result result = new Result();
		//检测用户名是否存在
		User has_user = userDao.findByName(name);
		if(has_user!=null){//已存在
			result.setStatus(1);
			result.setMsg("用户名已存在");
			return result;
		}
		//执行注册逻辑
		User user = new User();
		//设置用户ID
		String userId = BlogUtil.createUUID();
		user.setCn_user_id(userId);
		user.setCn_user_name(name);//设置用户名
		user.setCn_user_nick(nick);//设置昵称
		//密码加密,设置密码
		String md5_password = BlogUtil.md5(password);
		user.setCn_user_password(md5_password);
		userDao.save(user);//添加用户
		
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	


}
