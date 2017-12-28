package service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import bean.LoginVerification;
import dao.DLoader;
import javafx.collections.ObservableList;
import ui.MultiScreenFramework;
import utility.LoginVariable;

public class LicenseAuthentication {
//	public static void main(String args[]) throws SQLException, IOException {
//		new LicenseAuthentication().macAddressAuthentication();
//	}

	public boolean macAddressAuthentication(String username,String password) throws SQLException, IOException {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			logger.info("IP Address: "+ip);
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			logger.info("mac address: "+sb.toString());
			ObservableList<LoginVerification> loginverificationlist = DLoader.getSingeletonInstanceOfLoader().usernamepasswordVerfication(sb.toString(),username,password);
			if(loginverificationlist.size()>0)
			{
				setLoginVariable(loginverificationlist);
				return true;
			}
			return false;
		} catch (UnknownHostException e) {
			logger.error("Error while login verification", e);
			e.printStackTrace();
			return false;
		} catch (SocketException e) {

			e.printStackTrace();
			return false;
		}

	}
	
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);
	
	public void setLoginVariable(ObservableList<LoginVerification> loginverificationlist){
		
		try{
		for(LoginVerification log : loginverificationlist)
		{
			System.out.println(log.isNewchallanaccess());
			LoginVariable.setJarversion(log.getJarversion());
			LoginVariable.setFirmname(log.getFirmname());
			LoginVariable.setGstin(log.getGstin());
			LoginVariable.setFilestoreaddress(log.getFilestoreaddress());
			LoginVariable.setNewchallanaccess(log.isNewchallanaccess());
			LoginVariable.setSortandfilteraccess(log.isSortandfilteraccess());
			LoginVariable.setNewassigneeaccess(log.isNewassigneeaccess());
			LoginVariable.setLogstoreaddress(log.getLogstoreaddress());
			LoginVariable.setLicenseid(log.getLicenseid());
			LoginVariable.setLogemailaddress(log.getLogemailaddress());
			LoginVariable.setLogemailpassword(log.getLogemailpassword());

		}}catch(Exception e){
			logger.error("Some Error Occured While Setting LoginVariables",e);
		}
	}
}
