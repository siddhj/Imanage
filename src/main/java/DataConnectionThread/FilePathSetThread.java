package DataConnectionThread;

import java.sql.SQLException;

import dao.DLoader;

public class FilePathSetThread implements Runnable{
	private String logfilepath,challanpdfpath;
	private int licenseid;
	public FilePathSetThread(String logfilepath,String challanpdfpath,int licenseid){
		this.logfilepath=logfilepath;
		this.licenseid=licenseid;
		this.challanpdfpath=challanpdfpath;
	}
	
	@Override
	public void run() {
		try {
			DLoader.getSingeletonInstanceOfLoader().setFilePath(logfilepath, challanpdfpath,licenseid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
