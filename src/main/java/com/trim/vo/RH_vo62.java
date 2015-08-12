package com.trim.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RH_vo62 {
	/**
	 * e' un link al record 61 originale inserito per facilitare la conversione
	 */
	private RH_vo61 r61;

	/**
	 * e' un link al record RH originale inserito per facilitare la conversione
	 */
	private Properties rh;

	/**
	 * questo campo rappresenta i dati 62
	 */
	private Properties data;

	/**
	 * lista di record 63 agganciati al 62 verra' specializzato con i diversi
	 * tipi
	 */
	private List<Properties> r63;

	public RH_vo61 getR61() {
		return r61;
	}

	public void setR61(RH_vo61 r61) {
		this.r61 = r61;
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

	public List<Properties> getR63() {
		if (r63 == null) {
			r63 = new ArrayList<Properties>();
		}
		return r63;
	}

	public void setR63(List<Properties> r63) {
		this.r63 = r63;
	}

}