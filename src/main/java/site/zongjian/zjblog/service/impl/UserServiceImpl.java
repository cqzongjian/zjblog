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
	@Resource//ע��
	private UserDao userDao;
	
	public Result checkLogin(String name, String password) {
		Result result = new Result();
		User user = userDao.findByName(name);
		//����û���
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û�������");
//			result.setData(null);
			return result;
		}
		//�������
		//���û���������ļ���
		String md5Password = BlogUtil.md5(password);
		//�ȶ����ݿ�������
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û�����������ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		//���û����ID����
		result.setData(user.getCn_user_id());
		return result;
	}


	public Result registUser(
		String name, String nick, String password) {
		
		Result result = new Result();
		//����û����Ƿ����
		User has_user = userDao.findByName(name);
		if(has_user!=null){//�Ѵ���
			result.setStatus(1);
			result.setMsg("�û����Ѵ���");
			return result;
		}
		//ִ��ע���߼�
		User user = new User();
		//�����û�ID
		String userId = BlogUtil.createUUID();
		user.setCn_user_id(userId);
		user.setCn_user_name(name);//�����û���
		user.setCn_user_nick(nick);//�����ǳ�
		//�������,��������
		String md5_password = BlogUtil.md5(password);
		user.setCn_user_password(md5_password);
		userDao.save(user);//����û�
		
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}
	


}
