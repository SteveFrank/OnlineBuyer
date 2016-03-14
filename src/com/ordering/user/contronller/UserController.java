package com.ordering.user.contronller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.cart.po.Cart;
import com.ordering.user.po.User;
import com.ordering.user.service.UserService;
import com.ordering.user.service.impl.UserException;
import com.ordering.utils.CommonUtils;
import com.ordering.utils.Mail;
import com.ordering.utils.MailUtils;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 用户激活功能
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/active")
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * 1、获取惨呼激活码
		 * 2、调用service方法完成激活
		 * 	>保存异常信息到request域中，转发到msg.jsp
		 * 3、保存成功信息到request域，转发到msg.jsp
		 */
		String code = request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜您，已经激活成功了，请您登陆书城完成注册！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "jsps/msg";
	}
	/**
	 * 登陆功能
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/loginUser")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 1、封装表单数据到form
		 * 2、输入校验（暂时不写）
		 * 3、调用service完成激活
		 * 4、保存用户信息道session
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			request.getSession().setAttribute("cart", new Cart());
			return "index";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "jsps/user/login";
		}
	}
	
	@RequestMapping(value="/registUser")
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//激活码
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		form.setState(false);
		
		//输入校验（利用Map进行相关保存）
		Map<String, String> errors = new HashMap<String, String>();
		
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空!");
		} else if(username.length() < 3 || username.length() > 10){
			errors.put("username", "用户名长度需要控制在3~10位之间!");
		}
		
		String password = form.getUsername();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空!");
		} else if(password.length() < 3 || password.length() > 10){
			errors.put("password", "密码长度需要控制在3~10位之间!");
		}
		
		String email = form.getUsername();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "邮箱地址不能为空!");
		} else if(email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){//利用正则表达式
			errors.put("email", "Email格式错误!");
		}
		
		//判断是否存在错误信息
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "jsps/user/regist";
		}
		
		
		try {
			userService.regist(form);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "jsps/user/regist";
		}
		
		//发邮件激活
		//准备配置文件
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");
		String uname = props.getProperty("uname");
		String pwd = props.getProperty("pwd");
		String from = props.getProperty("from");
		String to = form.getEmail();
		String subject = props.getProperty("subject");
		String content = props.getProperty("content");
		content = MessageFormat.format(content, form.getCode());
		
		Session session = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from,to,subject,content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
		}
		
		//保存成功信息
		//转发到msg.jsp
		request.setAttribute("msg", "恭喜您，注册成功，请到邮箱激活");
		return "jsps/msg";
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		//销毁session
		request.getSession().invalidate();
		return "jsps/main";
	}
	
}
