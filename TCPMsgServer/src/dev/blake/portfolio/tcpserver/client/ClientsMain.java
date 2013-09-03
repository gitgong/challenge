package dev.blake.portfolio.tcpserver.client;



public class ClientsMain {
    public static void main(String[] args){

        Thread client = new TCPMsgClient("sfo");
        client.start();

        client = new TCPMsgClient("pdx");
        client.start();

        client = new TCPMsgClient("chi");
        client.start();

        client = new TCPMsgClient("lax");
        client.start();

        client = new TCPMsgClient("miami");
        client.start();

        client = new TCPMsgClient("tokyo");
        client.start();

        client = new TCPMsgClient("bangkok");
        client.start();

        client = new TCPMsgClient("lond");
        client.start();

        client = new TCPMsgClient("shang");
        client.start();

        client = new TCPMsgClient("dubai");
        client.start();

        // 10 more
        client = new TCPMsgClient("yosemite");
        client.start();

        client = new TCPMsgClient("joshua");
        client.start();

        client = new TCPMsgClient("yellow");
        client.start();

        client = new TCPMsgClient("golden");
        client.start();

        client = new TCPMsgClient("griffith");
        client.start();

        client = new TCPMsgClient("central");
        client.start();

        client = new TCPMsgClient("sutro");
        client.start();

        client = new TCPMsgClient("marin");
        client.start();

        client = new TCPMsgClient("peoples");
        client.start();



    }
}
