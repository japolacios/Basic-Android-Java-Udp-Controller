import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import processing.core.PApplet;

public class Comunicacion extends Thread {
	
	PApplet app;
	private DatagramPacket dp;
	private DatagramSocket ds;
	private String comando = "", ip;
	private InetAddress ia, inet;
	private int evaluador=0;
	private int numEnemigos = 0;

	ArrayList<String> ipList;
	
	public Comunicacion(PApplet _app) {
		
		app=_app;
		ipList = new ArrayList<String>();
		//Set the network type
		try {
			ia = InetAddress.getLocalHost();
			ip = ia.getHostAddress();
			ip= ip.substring(0, ip.lastIndexOf("."));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		try {
			ds = new DatagramSocket(5000);
			byte[] bytes = new byte [1000];
			dp = new DatagramPacket(bytes, bytes.length);
		}catch (SocketException e){
			e.printStackTrace();
		}
		
		//Start thread to search Ip's
		new Thread(recorrido()).start();

	}
	
	@Override
	public void run() { 
		//Recive Messages
		while(true){
			try {
				ds.receive(dp);
				comando = new String (dp.getData(), dp.getOffset(),dp.getLength());
				System.out.println("Command Recived -> " + comando);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
//	Creo un nuevo hilo para el rastreo de las ips
	private Runnable recorrido() {
		Runnable r = new Runnable() {
			
			public void run() {
				while (true){
					//Loop the Network Ips
					for (int i = 1; i < 255; i++) { 
						String host = ip + "." +i;
						try {
							inet = InetAddress.getByName(host);
									try {
										//Check if ip is reachable and add to list
								if(inet.isReachable(60)){
									
									numEnemigos++;
									ipList.add(host);

									System.out.println(host + " Reachable");
								}else{
									System.out.println(host + " Not Reachable");
								}
							} catch (IOException e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						} catch (UnknownHostException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}
		};
		return r;
	}

	
	public int getNumEnemy(){
		return numEnemigos;
	}

	public String getComando() {
		return comando;
	}

	public void setComando(String _comando) {
		comando = _comando;
		System.out.println("Command Changed to " + comando);
	}
	
	
}
