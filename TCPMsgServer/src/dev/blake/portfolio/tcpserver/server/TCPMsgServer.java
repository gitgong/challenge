package dev.blake.portfolio.tcpserver.server;

/**
 * Author: Blake Rogers
 * Date: 7/1/13
 */

import dev.blake.portfolio.tcpserver.msg.ValidMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMsgServer extends Thread {


    public TCPMsgServer(){
       super("TCPMsgServer");
    }

    public static void main(String args[]){
        Thread server = new TCPMsgServer();
        server.start();
    }
    @Override
    public void run() {
        String dataInStr;
        ServerSocket welcomeSocket=null;
        try {
            welcomeSocket = new ServerSocket(4848);
            //adds backlog queue of 100, instead of default 50  msgs
            //welcomeSocket = new ServerSocket(4848, 100);
            System.out.println("Starting TCP Message Server...");
            int runs = 0;
            while(true)
            {
                Socket connectionSocket = welcomeSocket.accept();
                //set receivebuffer to see if this works...
               // connectionSocket.setReceiveBufferSize(8192);
                if(connectionSocket!=null)
                	System.out.println("Connection accepted");
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                dataInStr = inFromClient.readLine();
                if(dataInStr != null){
                	System.out.println("Server Read a message");
                }
                String delim = "##";
                String[] tokens = dataInStr.split(delim );
                try {
                    runs++;
                    System.out.println("Message #: " + runs );
                    //message validation takes place here...
                    ValidMessage msg = new ValidMessage(Byte.valueOf(tokens[0]), Short.valueOf(tokens[1]), Integer.valueOf(tokens[2]), tokens[3]);
                    System.out.println("Version="+msg.getVersion());
                    System.out.println("Message Type=" + msg.getMsgType());
                    System.out.println("User ID=" + msg.getUserId());
                    System.out.println("Payload=" + msg.getPayLoad() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("Bad message data format, skipped message: " + e.getMessage());
                } finally{
                    //close client socket and stream
                    connectionSocket.close();
                    inFromClient.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException: Stopping TCP Message Server...");
            System.exit(-1);
        }finally{
            try {
                if(welcomeSocket!=null)
                    welcomeSocket.close();
                System.out.println("Stopping TCP Message Server...");
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("Stopping TCP Message Server...");
                System.exit(-1);
            }

        }
    }
}