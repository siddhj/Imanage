package DataConnectionThread;

import java.io.IOException;
import java.sql.SQLException;

import bean.Chalan;
import dao.DChalan;

public class ChallanInsertThread implements Runnable {

	private Chalan chalan;
	private long aggregatechallanid;
	
	public ChallanInsertThread(Chalan chalan,long aggregatechallanid){
		this.chalan=chalan;
		this.aggregatechallanid=aggregatechallanid;
	}
	
	@Override
	public void run() {
		try {
			DChalan.getSingeletonInstance().chalanDataInsert(chalan, aggregatechallanid);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
