/**
 * 
 */
package com.wq.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *<p>用来发送邮件的工具类</p> 
 *
 * @author 王庆
 * @date 2016年12月7日 下午6:03:33
 */
public class MailUtils {
	/*
	 * @param to 收件人
	 * @param code 激活码
	*/
	public static void sendMail(String to, String code){
		/*
		 * 1.获得session对象
		 * 2.创建一个代表邮件的对象Message
		 * 3.发送邮件Transport
		 * 在使用qq邮箱做服务游戏，由于在第三方登陆需要授权码，比较麻烦
		*/
		//1.获得连接对象
//		String serviceEmail = "15927697562@163.com";
//		String emailPassword = "MOMO1065804178";
		String serviceEmail = "service@shop.com";
		String emailPassword = "123";
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		props.setProperty("mail.transport.protocol", "smtp");
//		props.setProperty("mail.smtp.starttls.enable", "true");
		/*//配置JavaMail的Properties时，不要指定“mail.smtp.socketFactory.class”，因为TLS使用的是普通的Socket。然后指定属性“mail.smtp.starttls.enable”为“true”。
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); */ 
//		props.setProperty("mail.smtp.port", "587");
//		props.setProperty("mail.smtp.socketFactory.port", "587");
		
//		props.setProperty("mail.smtp.auth", "true");	本地邮箱不需要这个
		Session session = Session.getInstance(props, new Authenticator() {

			/* (non-Javadoc)
			 * @see javax.mail.Authenticator#getPasswordAuthentication()
			 */
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(serviceEmail, emailPassword);
			}
			
		});
		
		//2.获得邮件对象
		Message message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress(serviceEmail));
			//设置收件人  	抄送CC 密送BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("来自天堂的一封激活邮件----------");
			message.setContent("<h1>天堂的激活邮件，点击下面链接激活</h1><h3><a href='http://192.168.199.151:8080/SSHShop/user_active.action?code="+code+"'>http://192.168.199.151:8080/SSHShop/user_active.action?"+code+"</a></h3>", "text/html; charset=utf-8");
			
			//3.发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			
			MailUtils.sendMail("aaa@shop.com", "sdfds46");
		}
	}
}
