package com;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class CreateFileAndFolder {

	public static void main(String args[])
	{
		CreateFileAndFolder create = new CreateFileAndFolder();
		//create.findNameOfDisk();
		create.createNewFolder();
	}
	
	public void findNameOfDisk(){
		File[] drives = File.listRoots();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		if (drives != null && drives.length > 0) {
			for (File aDrive : drives) {
				String drivetype = fsv.getSystemTypeDescription(aDrive);
				System.out.println("Drive type: "+drivetype);
				System.out.println(aDrive);
				
				if(drivetype.contains("Local Disk"))
				{
					System.out.println("It is Local");
				}
				
			}
		} 
	}
	
	
	public void createNewFolder(){
		System.out.println(new File("F:\\imanage\\logs\\").mkdirs());
	}
	
}
