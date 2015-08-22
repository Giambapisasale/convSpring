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
	 * lista di record 63 agganciati al 62 specializzato con i diversi tipi e
	 * casi, compreso caso base
	 */
	private Properties r63;

	private Properties r63_ZZ1;

	private Properties r63_ZZ2;

	private Properties r63_ZZ3;

	private Properties r63_KKK;

	private Properties r63_YYY;

	private Properties r63_ID1;
	private Properties r63_RI1;
	private Properties r63_RI2;

	public Properties getR63_ID1() {
		return r63_ID1;
	}

	public void setR63_ID1(Properties r63_ID1) {
		this.r63_ID1 = r63_ID1;
	}

	public Properties getR63_RI1() {
		return r63_RI1;
	}

	public void setR63_RI1(Properties r63_RI1) {
		this.r63_RI1 = r63_RI1;
	}

	public Properties getR63_RI2() {
		return r63_RI2;
	}

	public void setR63_RI2(Properties r63_RI2) {
		this.r63_RI2 = r63_RI2;
	}

	public Properties getR63_KKK() {
		return r63_KKK;
	}

	public void setR63_KKK(Properties r63_KKK) {
		this.r63_KKK = r63_KKK;
	}

	public Properties getR63_YYY() {
		return r63_YYY;
	}

	public void setR63_YYY(Properties r63_YYY) {
		this.r63_YYY = r63_YYY;
	}

	public Properties getR63_ZZ2() {
		return r63_ZZ2;
	}

	public void setR63_ZZ2(Properties r63_ZZ2) {
		this.r63_ZZ2 = r63_ZZ2;
	}

	public Properties getR63_ZZ3() {
		return r63_ZZ3;
	}

	public void setR63_ZZ3(Properties r63_ZZ3) {
		this.r63_ZZ3 = r63_ZZ3;
	}

	public Properties getR63_ZZ1() {
		return r63_ZZ1;
	}

	public void setR63_ZZ1(Properties r63_ZZ1) {
		this.r63_ZZ1 = r63_ZZ1;
	}

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

	public Properties getR63() {
		return r63;
	}

	public void setR63(Properties r63) {
		this.r63 = r63;
	}

}