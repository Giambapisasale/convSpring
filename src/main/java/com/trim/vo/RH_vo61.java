package com.trim.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RH_vo61 {
	
	/**
	 * e' un link al record RH originale inserito per facilitare la conversione
	 */
	private Properties rh;

	/**
	 * questo campo rappresenta i dati 61
	 */
	private Properties data;

	/**
	 * lista di record 62 agganciati al 61 
	 */
	private List<RH_vo62> r62;
	
	

	public List<RH_vo62> getR62() {
		if(r62==null) {
			r62 = new ArrayList<RH_vo62>();
		}
		return r62;
	}

	public void setR62(List<RH_vo62> r62) {
		this.r62 = r62;
	}

	public Properties getRh() {
		return rh;
	}

	public void setRh(Properties rh) {
		this.rh = rh;
	}

	public Properties getData() {
		return data;
	}

	public void setData(Properties data) {
		this.data = data;
	}

	

}