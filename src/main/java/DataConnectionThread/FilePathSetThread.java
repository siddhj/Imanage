package DataConnectionThread;

import java.sql.SQLException;

import dao.DLoader;

public class FilePathSetThread implements Runnable{
	private String logfilepath;
	private int licenseid;
	public FilePathSetThread(String logfilepath,int licenseid){
		this.logfilepath=logfilepath;
		this.licenseid=licenseid;
	}
	
	@Override
	public void run() {
		try {
			DLoader.getSingeletonInstanceOfLoader().setFilePath(logfilepath, licenseid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
