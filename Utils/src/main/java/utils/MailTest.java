package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by Adam Yao on 2017/6/11
 */
public class MailTest {
    //15. Java Mail
    // 发送Mail
    protected String msgRecIp = "hxydream@163.com";
    protected String msgSubject = "babytree";
    protected String msgCc = "nobody@erewhon.com";
    protected String msgBody = "test body";
    protected static Session session;
    protected static Message msg;
    public static void main(String[] args) throws MessagingException {

        // 发送MIME邮件
        Multipart mp = new MimeMultipart();
        BodyPart textPart = new MimeBodyPart();
        textPart.setText("message_body");  // 设置类型"text/plain"
        BodyPart pixPart = new MimeBodyPart();
        pixPart.setContent("html_data", "text/html");
        mp.addBodyPart(textPart);
        mp.addBodyPart(pixPart);
        msg.setContent(mp);
        Transport.send(msg);

        // 读Mail
        Store store = session.getStore("protocol");
        String host = "";
        String user = "";
        String password = "";
        store.connect(host, user, password);
        Folder rf;
        String root = "";
        rf = store.getFolder(root);
        rf = store.getDefaultFolder();
        rf.open(Folder.READ_WRITE);

    }

    public void doSend() throws MessagingException {
        // 创建属性文件
        Properties props = new Properties();
        props.put("mail.smtp.host", "mailhost");
        // 创建Session对象
        session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        msg = new MimeMessage(session); // 创建邮件
        msg.setFrom(new InternetAddress("nobody@host.domain"));
        InternetAddress toAddr = new InternetAddress(msgRecIp);
        msg.addRecipient(Message.RecipientType.TO, toAddr);
        InternetAddress ccAddr = new InternetAddress(msgCc);
        msg.addRecipient(Message.RecipientType.CC, ccAddr);
        msg.setSubject(msgSubject);
        msg.setText(msgBody);
        Transport.send(msg);
    }
}
