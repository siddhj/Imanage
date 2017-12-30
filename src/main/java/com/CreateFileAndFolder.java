package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.filechooser.FileSystemView;

public class CreateFileAndFolder {

	public static void main(String args[]) throws IOException {
		checkAndCreateNewLogFile();
	}

	public static void checkAndCreateNewLogFile() throws IOException {
		// find the drives. //priority D,E,F and so on.
		ArrayList<String> drivenames = getDriveNames();
		Collections.sort(drivenames);
		String uri = "IManage\\Logs\\";
		outerloop: for (String name : drivenames) {
			// loop through name and find if present //check for each standard
			// path
			// exsist.
			if (isPathValid(createPathURI(name, uri,"info.log"))) {
				System.out.println("Folder is already present");
				setLogfilepath(name+uri+"info.log");
				System.out.println(name+uri+"info.log");
				break outerloop;
			} else {
				createNewFileInFolder(name, "info.log", uri);
				break outerloop;
			}
		}
	}

	public void findNameOfDisk() {
		File[] drives = File.listRoots();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		if (drives != null && drives.length > 0) {
			for (File aDrive : drives) {
				String drivetype = fsv.getSystemTypeDescription(aDrive);
				System.out.println("Drive type: " + drivetype);
				System.out.println(aDrive);

				if (drivetype.contains("Local Disk")) {
					System.out.println("It is Local");
				}

			}
		}
	}

	public static ArrayList<String> getDriveNames() {
		File[] drives = File.listRoots();
		ArrayList<String> drivename = new ArrayList<>();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		if (drives != null && drives.length > 0) {
			for (File aDrive : drives) {
				String drivetype = fsv.getSystemTypeDescription(aDrive);
				if (drivetype.contains("Local Disk") && !aDrive.toString().contains("C:\\")) {
					drivename.add(aDrive.toString());
				}
			}
		}
		return drivename;
	}

	public static String createPathURI(String drivename, String url, String filename) {
		String uri = drivename +url+filename;
		return uri;
	}

	public static boolean isPathValid(String uri) {
		Path path = Paths.get(uri);
		if (Files.exists(path)) {
			return true;
		}
		return false;
	}
	private static String logfilepath;
	public static String getLogfilepath() {
		return logfilepath;
	}

	public static void setLogfilepath(String logfilepath) {
		CreateFileAndFolder.logfilepath = logfilepath;
	}

	public static void createNewFileInFolder(String drivename, String filename, String uri) throws IOException {
		System.out.println(drivename + uri);
		if (new File(drivename + uri).mkdirs()) {
			System.out.println("new folder created");
			if (new File(drivename + uri + filename).createNewFile()) {
				setLogfilepath(drivename+uri+filename);
				System.out.println("new file created");
			}
		}
	}

	// new address for pdf file
	public static void createNewFolderForPDF(String drivename) {
		if (new File(drivename + "IManage\\Challanpdf\\").mkdirs()) {
			System.out.println("new folder created");
		}
	}

	public static void createNewPath() {

	}

}
