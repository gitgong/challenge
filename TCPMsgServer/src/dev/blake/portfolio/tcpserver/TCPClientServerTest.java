package dev.blake.portfolio.tcpserver;

import dev.blake.portfolio.tcpserver.client.ClientsMain;
import dev.blake.portfolio.tcpserver.server.TCPMsgServer;

public class TCPClientServerTest extends Thread {

	public static void main(String[] args){
		TCPClientServerTest test = new TCPClientServerTest ();
		test.start();
	}
	
	public void run(){
		
		try {
			TCPMsgServer.main(new String[0]);
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ClientsMain.main(new String[0]);
		}

		
	}

}
