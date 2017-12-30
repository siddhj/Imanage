package com;


import java.util.Date;

public class multithreadtest {
public static void main(String argsp[]){
	
	for(int i=0;i<20;i++)
	{
		Thread t = new Thread(){
			public void run(){
				long l = new Date().getTime();
				for(int j=0;j<100;j++)
				System.out.println(l);
			}
		};
		t.start();
	}
}
}
