/**
 * 6d Technologies
 * Copyright 2016
 * All Rights Reserved.
 */
package com.example.filescanner.client;
/**
 * 
 * @author afthab
 * 8-FEB-2016
 */

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.example.filescanner.imp.FileNotifier;
import com.example.filescanner.imp.FileScanManager;

public class MainClass {
	private static Logger logger = Logger.getLogger(MainClass.class);
	private static String path = null;

	public static void main(String[] args) {
		System.out.println("------------------------Application Started-------------------------");
		logger.info("-------------------Application Started-----------------");
		
		try {
			path = new File(".").getCanonicalPath().concat(File.separator)+ "config" + File.separator +"files";
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileScanManager.getManager().load(path);
		
		if(path != null && !path.equals("")){
			TimerTask task = new FileNotifier(path) {

				protected void onChange( File file, String action ) {
					FileScanManager.getManager().next(file.getName(), path);
				}
			};

			Timer timer = new Timer();
			timer.schedule( task , new Date(), 1000 );
		}
	}
}