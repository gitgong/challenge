package dev.blake.portfolio.email;

public class EmailDataModel {
	private String sender;
	private String recip;
	private String subject;
	private String bodyText;
	public EmailDataModel(String sen, String rec, String sub, String bod){
		sender = sen;
		recip = rec;
		subject = sub;
		bodyText = bod;
	}
	public String getSender(){
		return sender;
	}
	public String[] getRecipients(){
		return new String[]{recip};
	}
	public String getSubject(){
		return subject;
	}
	public String getBodyText(){
		return bodyText;
	}
}