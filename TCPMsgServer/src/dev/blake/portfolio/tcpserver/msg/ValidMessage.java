package dev.blake.portfolio.tcpserver.msg;

/**
 * Created with IntelliJ IDEA.
 * User: Blake
 * Date: 7/1/13
 * Time: 2:34 PM

 */
public class ValidMessage {
    byte version;   //1 byte, max= 127, min -128   (over 255?)
    short msgType;      //2 byte  int (a short)      max 32,767
    int userId;        // 4 byte   int      max 2,147,483,647
    String payLoad;      // ascii any length

    public ValidMessage(byte version, short msgType, int userId, String payload){
        this.version=version;
        this.msgType=msgType;
        this.userId=userId;
        this.payLoad=payload;
    }

    public int getUserId() {
        return userId;
    }
    public byte getVersion() {
        return version;
    }
    public String getPayLoad() {
        return payLoad;
    }
    public short getMsgType() {
        return msgType;
    }
}
