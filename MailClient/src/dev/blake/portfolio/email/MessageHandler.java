package dev.blake.portfolio.email;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class MessageHandler {
	public static javax.mail.Session getEmailServerSession(String smtp_host){
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp_host);
		props.put("mail.debug", "true");
		javax.mail.Session ses = javax.mail.Session.getDefaultInstance(props, null);
		return ses;
	}
	public static void sendMessage(MimeMessage msg, URLName smtpUrl, javax.mail.Session
			ses){
		try{
			Transport theTransp = ses.getTransport(smtpUrl);
			theTransp.send(msg);
			theTransp.close();
			System.out.println("Sent msg, msg id #" +msg.getMessageID());
		}
		catch(NoSuchProviderException nspe){
			System.out.println("nspe: " + nspe.getMessage());
		}
		catch(MessagingException mex){
			System.out.println("mex: " + mex.getMessage());
		}
	}
	public static Message[] checkMessages(URLName pop3Url, javax.mail.Session ses){
		Message[] msgs = null;
		try{
			Store theEmailStorage = ses.getStore(pop3Url);
			theEmailStorage.connect();
			Folder inBox = theEmailStorage.getFolder("INBOX");
			inBox.open(Folder.READ_ONLY);
			msgs = inBox.getMessages();
			theEmailStorage.close();
		}
		catch(NoSuchProviderException nspe){
			System.out.println("nspe: " + nspe.getMessage());
		}
		catch(MessagingException mex){
			System.out.println("mex: " + mex.getMessage());
		}
		return msgs;
	}
	public static Message buildSimpleMessage(EmailDataModel model, javax.mail.Session
			aSession){
		Message msg = null;
		boolean sendHTML = true;
		try{
			msg = new MimeMessage(aSession);
			msg.setFrom(new InternetAddress(model.getSender()));
			String[] toStrings = model.getRecipients();
			InternetAddress toAddresses[] = new InternetAddress[toStrings.length];
			for(int i = 0; i < toStrings.length; i++){
				InternetAddress address = new InternetAddress(toStrings[i]);
				toAddresses[i] = address;
			}
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(model.getSubject());
			msg.setSentDate(new Date());
			//create body -- todo: externalize html
			String text = model.getBodyText();
			StringBuffer sb = new StringBuffer();
			sb.append("<HTML>\n");
			sb.append("<HEAD>\n");
			sb.append("<TITLE>\n");
			sb.append("xxx email" + "\n");
			sb.append("</TITLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY>\n");
			sb.append("<br><br><br>\n");
			sb.append("This user has sent you something.\n");
			sb.append("<br><br>\n");
			sb.append("<b>" + text + "</b>" + "\n");
			sb.append("</BODY>\n");
			sb.append("</HTML>\n");
			if(sendHTML){
				try{
					addHTML(msg, sb.toString());
				}
				catch(IOException ioe){
					System.out.println(ioe.getMessage());
				}
			}
			else{
				msg.setText(model.getBodyText());
			}
			//end create body
		}catch (MessagingException mex) {
			System.out.println(mex.getMessage());
		}
		return msg;
	}
	private static void addHTML(Message msg, String bodyHTML)
			throws MessagingException, IOException {
		msg.setDataHandler(new DataHandler(
				new ByteArrayDataSource(bodyHTML, "text/html")));
	}
}
