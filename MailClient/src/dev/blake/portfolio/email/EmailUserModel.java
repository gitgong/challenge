package dev.blake.portfolio.email;

public class EmailUserModel{
	private String userName;
	private String password;
	private String emailAddress;
	public EmailUserModel(String user, String pass, String address){
		userName=user;
		password=pass;
		emailAddress=address;
	}
	public String getUserName(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	public String getEmailAddress(){
		return emailAddress;
	}
}
