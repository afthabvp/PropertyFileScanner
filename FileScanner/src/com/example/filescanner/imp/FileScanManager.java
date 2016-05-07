package com.example.filescanner.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.example.filescanner.bean.PropertyBean;
import com.example.filescanner.bean.Result;


public class FileScanManager {

	private static Logger logger = Logger.getLogger(FileScanManager.class);
	private static FileScanManager manager = new FileScanManager();
	PropertyBean prop= PropertyBean.getInstance();

	private FileScanManager() {
	}

	public static FileScanManager getManager() {
		return manager;
	}

	private void init(String path) {

		try {
			Properties properties = null;
			properties = getProperties("propA.properties",path);
			prop.setOldA(properties);

			properties = getProperties("propB.properties",path);
			prop.setOldB(properties);

			properties = getProperties("propC.properties",path);
			prop.setOldC(properties);

		} catch(Exception ex) {
			logger.error("load, ",ex);
		}
	}
	public void load(String path) {
		manager.init(path);
	}

	public Properties getProperties(String fileName, String path) throws IOException{
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream(path + File.separator + fileName );
		prop.load(input);
		return prop;


	}

	public void next (String filename,String path) {
		try {
			Properties properties = null;
			if(filename.equals("propA.properties")){
				properties = getProperties("propA.properties",path);
				prop.setCurrentA(properties);
				output(filename,prop.getOldA(),prop.getCurrentA());
				prop.setOldA(prop.getCurrentA());
			}else if(filename.equals("propB.properties")){	
				properties = getProperties("propB.properties",path);
				prop.setCurrentB(properties);
				output(filename,prop.getOldB(),prop.getCurrentB());	
				prop.setOldB(prop.getCurrentB());
			}else if(filename.equals("propC.properties")){
				properties = getProperties("propC.properties",path);
				prop.setCurrentC(properties);
				output(filename,prop.getOldC(),prop.getCurrentC());
				prop.setOldB(prop.getCurrentB());

			}



		} catch(Exception ex) {
			logger.error("next, ",ex);
		}
	}

	public void output(String filename, Properties old, Properties current) {
		Result res = new Result();
		res.setFileName(filename);
		Enumeration<?> e = old.propertyNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String valueOld = (String)old.get(key);
			String value = (String)current.get(key);
			res.setKey(key);
			res.setOldValue(valueOld);
			if(value == null){
				res.setNewValue(null);
				print(res);
			}else{
				if(!valueOld.equals(value)){
					res.setNewValue(value);
					print(res);
				}

			} 
		}

		Enumeration<?> currentE = current.propertyNames();
		while (currentE.hasMoreElements()) {
			String key = (String) currentE.nextElement();
			String value = (String)old.get(key);
			String valueNew = (String) current.get(key);
			if(value == null){
				res.setKey(key);
				res.setOldValue(null);
				res.setNewValue(valueNew);
				print(res);
			}
		}

	}

	public void print(Result res) {
		System.out.println("File Changed : "+res.getFileName());
		System.out.println("Property Changed : "+res.getKey());
		System.out.println("Old Value: "+res.getOldValue() );
		System.out.println("New Value: "+res.getNewValue());

	}

}
