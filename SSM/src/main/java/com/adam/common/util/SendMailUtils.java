package com.adam.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.*;

/**
 * 使用SMTP协议发送电子邮件
 */ 
public class SendMailUtils { 
	public static final String CONF_RESOURCE = "resource";
    // 邮件发送协议 
    private final static String PROTOCOL = Resources.instance(CONF_RESOURCE).getAttributeValue("email_protocol");
    // SMTP邮件服务器 
    private final static String HOST = Resources.instance(CONF_RESOURCE).getAttributeValue("email_host");
    // SMTP邮件服务器默认端口 
    private final static String PORT = Resources.instance(CONF_RESOURCE).getAttributeValue("email_port");
    //用户名
    private final static String UNAME = Resources.instance(CONF_RESOURCE).getAttributeValue("email_uname");
    //密码
    private final static String UPASS = Resources.instance(CONF_RESOURCE).getAttributeValue("email_upass");
    // 邮件标题
    private final static String SUBJECT = Resources.instance(CONF_RESOURCE).getAttributeValue("email_subject");
    // 是否要求身份认证 
    private final static String IS_AUTH = "true"; 
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） 
    private final static String IS_ENABLED_DEBUG_MOD = "true"; 
    // 发件人 
    private static String from = Resources.instance(CONF_RESOURCE).getAttributeValue("email_from");
    // 初始化连接邮件服务器的会话信息 
    private static Properties props = null; 
    static { 
    	props = new Properties(); 
        props.setProperty("mail.transport.protocol", PROTOCOL); 
        props.setProperty("mail.smtp.host", HOST); 
        props.setProperty("mail.smtp.port", PORT); 
        props.setProperty("mail.smtp.auth", IS_AUTH); 
        props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD); 
    } 

    public static void main(String[] args) throws Exception { 
        sendHtmlEmail("<span style='color:red;'>HTML邮件内容</span>","yaodwwy@qq.com"); 
    } 
    /**
     * 发送简单的html邮件
     */ 
    public static void sendHtmlEmail(String htmlContent, String mailTo) throws Exception { 
        // 创建Session实例对象 
        Session session = Session.getInstance(props, new MyAuthenticator()); 
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
        // 设置邮件主题 
        message.setSubject(SUBJECT); 
        // 设置发送人 
        message.setFrom(new InternetAddress(from)); 
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置收件人 
        message.setRecipients(RecipientType.TO, InternetAddress.parse(mailTo)); 
        // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk 
        message.setContent(htmlContent,"text/html;charset=gbk"); 
        // 保存并生成最终的邮件内容 
        message.saveChanges(); 
        // 发送邮件 
        Transport.send(message); 
    } 
    /**
     * 向邮件服务器提交认证信息
     */ 
    static class MyAuthenticator extends Authenticator { 
        public MyAuthenticator() { 
            super(); 
        } 
        @Override 
        protected PasswordAuthentication getPasswordAuthentication() { 
            return new PasswordAuthentication(UNAME, UPASS); 
        } 
    } 
} 