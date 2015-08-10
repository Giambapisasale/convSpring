package com.trim.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RH_vo {
	private Properties rh;
	private Properties ef;

	private List<Properties> r61;
	private List<Properties> r62;
	private List<Properties> r63;
	private List<Properties> r64;
	private List<Properties> r65;

	public Properties getRh() {
		return rh;
	}

	public void setRh(Properties rh) {
		this.rh = rh;
	}

	public List<Properties> getR61() {
		if (r61 == null) {
			r61 = new ArrayList<Properties>();
		}
		return r61;
	}

	public void setR61(List<Properties> r61) {
		this.r61 = r61;
	}

	public List<Properties> getR62() {
		return r62;
	}

	public void setR62(List<Properties> r62) {
		this.r62 = r62;
	}

	public List<Properties> getR63() {
		return r63;
	}

	public void setR63(List<Properties> r63) {
		this.r63 = r63;
	}

	public List<Properties> getR64() {
		return r64;
	}

	public void setR64(List<Properties> r64) {
		this.r64 = r64;
	}

	public List<Properties> getR65() {
		return r65;
	}

	public void setR65(List<Properties> r65) {
		this.r65 = r65;
	}

	public Properties getEf() {
		return ef;
	}

	public void setEf(Properties ef) {
		this.ef = ef;
	}

}
