package site.zongjian.zjblog.service;

import site.zongjian.zjblog.util.Result;

public interface UserService {
	
	public Result registUser(String name, String nick, String password);

	public Result checkLogin(String name, String password);
	
}
