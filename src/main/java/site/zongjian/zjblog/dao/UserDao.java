package site.zongjian.zjblog.dao;

import org.springframework.stereotype.Component;
import site.zongjian.zjblog.entity.User;

@Component
public interface UserDao {
	public void save(User user);
	public User findByName(String name);
}
