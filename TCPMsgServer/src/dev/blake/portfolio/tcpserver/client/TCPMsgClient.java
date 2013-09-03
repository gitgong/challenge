package dev.blake.portfolio.tcpserver.client;

/**
 * Author: Blake Rogers
 * Date: 7/1/13
 */

import dev.blake.portfolio.tcpserver.msg.TestMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class TCPMsgClient extends Thread {

    String delim = "##";
    Socket clientSocket = null;

    String name;

    public TCPMsgClient(String name){
        super(name);
        this.name = name;
    }

    public TCPMsgClient(){
        super("TCPMsgClient");
        this.name=   "TCPMsgClient";
    }

    static TestMessage[] msgArr = msgArr = new TestMessage[6];
    static String[] versions = {"110","110","110","1100","110","110"};
    static String[] msgTypes={"1111","2222","3333","4444","5555","6666"};
    static String[] userIds={"51111111","5222222","53333333","544444444","55555555","56666666"};
    static String[] msgs = {"began before time forgot landmass",
            "it was a dark and stormy night man 234567@@@@ ",
            "deeper than the deepest deep and darker than the darkest depths",
            "banana apple mango papaya blueberry banana apple mango papaya blueberry 8888",
            "water water everywhere nor any drop to drink 56565665",
            "and some in dreams assured were of the spirit that plagued us so !!!!"};

    static{
        for(int i = 0; i < msgArr.length; i++){
            msgArr[i]= new TestMessage(versions[i], msgTypes[i], userIds[i],msgs[i]);
        }
    }

    public static void main(String args[]){
        Thread client = new TCPMsgClient();
        client.start();
    }

    @Override
    public void run(){
        System.out.println("TCP Client TCPMsgClientThread "+ name+ " start sending test messages.");
        try {
            int i = 0;
            for(; i < msgArr.length; ){
                clientSocket = new Socket("localhost", 4848);
                //sets socket buffer on client
               clientSocket.setSendBufferSize(16384);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                //threadsafe
                StringBuffer sb = new StringBuffer();
                sb.append(msgArr[i].getVersion());
                sb.append(delim);
                sb.append(msgArr[i].getMsgType());
                sb.append(delim);
                sb.append(msgArr[i].getUserId());
                sb.append(delim);
                sb.append(msgArr[i].getPayLoad() );
                outToServer.writeBytes(sb.toString() + '\n');
                try {
                    //wait between sending msg to server
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }  finally {
                    //just keep looping through array over and over....until its stopped.
                    i++;
                    if(i==5) i=0;
                    if(clientSocket!=null) clientSocket.close();
                    outToServer.close();
                }
            }
        }catch (SocketException e) {
            System.out.println("SocketException " + e.getMessage() ) ;
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage() ) ;
        } finally {
            System.out.println("Finally TCP Client "+ name+ " done sending test messages.");
        }
    }
}