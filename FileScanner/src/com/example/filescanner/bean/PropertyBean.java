package com.example.filescanner.bean;

import java.util.Properties;


public class PropertyBean {
	
	
	private static PropertyBean _manager;
	
	public static PropertyBean getInstance() {
		if(_manager == null) {
			_manager = new PropertyBean();
		}
		return _manager;
	}

	private PropertyBean(){
		
	}
	Properties currentA;
	Properties oldA;
	
	Properties currentB;
	Properties oldB;
	
	Properties currentC;
	Properties oldC;
	
	public Properties getCurrentA() {
		return currentA;
	}
	public void setCurrentA(Properties currentA) {
		this.currentA = currentA;
	}
	public Properties getOldA() {
		return oldA;
	}
	public void setOldA(Properties oldA) {
		this.oldA = oldA;
	}
	public Properties getCurrentB() {
		return currentB;
	}
	public void setCurrentB(Properties currentB) {
		this.currentB = currentB;
	}
	public Properties getOldB() {
		return oldB;
	}
	public void setOldB(Properties oldB) {
		this.oldB = oldB;
	}
	public Properties getCurrentC() {
		return currentC;
	}
	public void setCurrentC(Properties currentC) {
		this.currentC = currentC;
	}
	public Properties getOldC() {
		return oldC;
	}
	public void setOldC(Properties oldC) {
		this.oldC = oldC;
	}
	
	
}
