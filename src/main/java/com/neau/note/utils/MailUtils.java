package com.neau.note.utils;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailUtils {

	public static final String CONTENT_TYPE = "text/plain;charset=GBK";
	public static final String FROM_EMAIL = "xfneau@163.com";
	public static final String EMAIL_PASSWD = "242527ff";

	public static void sendmail(String subject, String contents,
			List<String> userEmailAddress) throws EmailException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setHostName("smtp.163.com");
		simpleEmail.setAuthentication(MailUtils.FROM_EMAIL,
				MailUtils.EMAIL_PASSWD);
		for (int i = 0; i < userEmailAddress.size(); i++) {
			simpleEmail.addTo(userEmailAddress.get(i));
		}
		simpleEmail.setFrom(MailUtils.FROM_EMAIL);
		simpleEmail.setSubject(subject);
		simpleEmail.setContent(contents, MailUtils.CONTENT_TYPE);
		simpleEmail.send();
	}
}
