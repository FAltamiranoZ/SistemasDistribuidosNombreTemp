/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

package javatcpsockets;

import java.net.*;
import java.io.*;

public class TCPServer {
    
    public static void main (String args[]) {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		while(true) {
			System.out.println("Waiting for messages..."); 
                        Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			Connection c = new Connection(clientSocket);
                        c.start();
		}
	} 
        catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
	    try {
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } 
            catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
            boolean flag = true;
            while(flag){ //No se exactamente cuantos loops debo tener, si tengo 
//menos entonces da null, si tengo más, entonces solo responde su límite y los 
//dos se quedan esperando; si en este momento tiro el server, se desconecta. Si
//hay varias conexiones a la vez, se comporta de igual manera en cualquiera de
//los 3 casos. Esto lo puedes ver en el output del server. Si acaba manda un 
//waiting for messages
                try {// an echo server
//		String data = in.readUTF();     
//              System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
//		out.writeUTF(data);
                int key = in.readInt();
                System.out.println(key);
                AddressBook addressBook = new AddressBook();
                String name = addressBook.getRecord(key).getName();
                System.out.println(name);
                if(name.equals("")){
                    out.writeUTF("No existe");
                    clientSocket.close();
                    flag = false;
                }
                else{
                    out.writeUTF(name);
                }
	    }
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    }
        }
    }
}
