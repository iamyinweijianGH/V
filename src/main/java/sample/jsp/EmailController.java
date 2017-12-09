package sample.jsp;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${mail.sender}")
    private String from;

	@RequestMapping("/email.do")
	@ResponseBody
	String home() {
		try {

			sendEmail();

			return "Email Sent!";

		} catch (Exception ex) {

			return "Error in sending email: " + ex;

		}

	}

	private void sendEmail(){

		try {
			MimeMessage message = sender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");//MimeMessageHelper(message);
			helper.setValidateAddresses(true);
			helper.setFrom(new InternetAddress(from));
			helper.setTo("13719249618@163.com");
			helper.setText("我是中国人");// 普通的文字
			//helper.setText("<font color='red'>爱我中华</font>", true);// 带html格式的文字
			//helper.addAttachment("ShadowsocksR-4.6.1-win.7z", new File("F:"+File.separator+"ShadowsocksR-4.6.1-win.7z"));
			helper.setSubject("我是中国人");

			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
