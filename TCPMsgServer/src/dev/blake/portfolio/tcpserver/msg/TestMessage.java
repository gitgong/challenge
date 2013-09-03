package dev.blake.portfolio.tcpserver.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Blake
 * Date: 7/1/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMessage {
    String version;   //1 byte, max= 127, min -128   (over 255?)
    String msgType;      //2 byte  int (a short)      max 32,767
    String userId;        // 4 byte   int      max 2,147,483,647
    String payLoad;      // ascii any length



    public TestMessage(String  version, String  msgType, String  userId, String payload){
        this.version=version;
        this.msgType=msgType;
        this.userId=userId;
        this.payLoad=payload;
    }

    public String getUserId() {
        return userId;
    }

    public String getVersion() {
        return version;

    }

    public String getPayLoad() {
        return payLoad;
    }

    public String getMsgType() {
        return msgType;
    }
}
