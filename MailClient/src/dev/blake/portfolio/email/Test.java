package dev.blake.portfolio.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
class Test {
	public static void main(String args[]){
		//user model
		String fromAdd = "from@xyz.com";
		String user = "bob";
		String pass = "xxxxx";
		EmailUserModel userModel = new EmailUserModel(user, pass, fromAdd);
		//email data model
		String toAdd = "to@xyz.com";
		String subject = "test6";
		String bodyText = "blah blah blah ya da ya da";
		EmailDataModel emailData = new
				EmailDataModel(userModel.getEmailAddress(), toAdd, subject, bodyText);
		//profile for sending authentication (non-authenticated - for 'transport')

		String smtp_host = "mail.xyz.org";
		URLName smtpHostUrl = new URLName("smtp", smtp_host, -1, null, null,
				null);
		//profile for checking authentication (authenticated - for 'store')
		String pop3_host = "xyz.com";
		URLName pop3HostUrl = new URLName("imap", pop3_host, -1, null,
				userModel.getUserName(), userModel.getPassword());
		//get session
		javax.mail.Session ses =
				MessageHandler.getEmailServerSession(smtp_host);
		//build and send
		MimeMessage msg = (MimeMessage)
				MessageHandler.buildSimpleMessage(emailData, ses);
		MessageHandler.sendMessage(msg, smtpHostUrl, ses);
		//check
		Message[] msgs = MessageHandler.checkMessages(pop3HostUrl, ses);
		System.out.println("You have " + msgs.length + " messages!");
	}
}
