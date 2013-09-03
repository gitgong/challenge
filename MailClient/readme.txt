readme

Example of implementation of Sun Javamail

supporting libraries:
activation.jar;
Imap.jar;
Mail.jar;
Mailapi.jar;
Pop3.jar;
Smtp.jar;

Application classes
Test
MessageHandler
EmailUserModel
EmailDataModel

support class
ByteArrayDataSource

To use component
(See Test for actual usage)

EmailUserModel - strings
user, password, emailaddress

EmailDataModel - strings
subject, body text, to-address, fromAddress from EmailUserModel

MessageHandler
to create session - pop3 host, smtp host

Issues
MessageHandler
1. getEmailServerSession() has no exception handling for problems getting session or validation.
2. sendMessage() has no validation on whether message 'actually' was received by email server or recipient.
3. checkMessages() is a simple version that simply returns an array of messages
- checked messages need to be flagged as checked or downloaded.
- data needs to be obtained from the message
4. buildSimpleMessage takes body text from the email data model and puts it
into a stringbuffer that gets html formatted. modifications to the html format have
to be made in the class and recompiled; this is not ideal