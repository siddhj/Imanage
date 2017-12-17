package service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import dao.DLoader;

public class LicenseAuthentication {
//	public static void main(String args[]) throws SQLException, IOException {
//		new LicenseAuthentication().macAddressAuthentication();
//	}

	public boolean macAddressAuthentication(String username,String password) throws SQLException, IOException {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());
			return DLoader.getSingeletonInstanceOfLoader().licenseVerfication(sb.toString(),username,password);
		} catch (UnknownHostException e) {

			e.printStackTrace();
			return false;
		} catch (SocketException e) {

			e.printStackTrace();
			return false;
		}

	}
}
