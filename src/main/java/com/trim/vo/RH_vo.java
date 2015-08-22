package com.trim.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RH_vo {
	private Properties rh;
	

	private List<RH_vo61> r61;

	public List<RH_vo61> getR61() {
		if (r61 == null) {
			r61 = new ArrayList<RH_vo61>();
		}
		return r61;
	}

	public void setR61(List<RH_vo61> r61) {
		this.r61 = r61;
	}

	public Properties getRh() {
		return rh;
	}

	public void setRh(Properties rh) {
		this.rh = rh;
	}
	
	public Properties getEf() {
		return ef;
	}

	public void setEf(Properties ef) {
		this.ef = ef;
	}

	private Properties ef;

}
