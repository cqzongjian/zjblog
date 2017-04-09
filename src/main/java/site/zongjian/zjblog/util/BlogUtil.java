package site.zongjian.zjblog.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.UUID;

public class BlogUtil {
	/**
	 * ����UUID�㷨����һ��Ψһ�Ե��ַ���
	 * @return ����ֵid
	 */
	public static String createUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * ��msg����MD5�㷨����,����һ��String���
	 * @param msg ����
	 * @return ����
	 */
	public static String md5(String msg){
		try{
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			//ԭʼ��Ϣinput
			byte[] input = msg.getBytes();
			//������Ϣoutput
			byte[] output = md.digest(input);//���ܴ���
			//����Base64����������outputת��String�ַ���
			String s = Base64.encodeBase64String(output);
			return s;
		}catch(Exception ex){
			System.out.println("md5����ʧ��");
			return null;
		}
	}
	
	public static void main(String[] args){
//		System.out.println(createId());
//		System.out.println(createId());
		System.out.println(md5("123456"));
//		System.out.println(md5("123456789ffasd"));
	}
	
}


