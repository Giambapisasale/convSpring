//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.03 at 06:46:19 PM CEST 
//


package com.trim.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CBICashAccount20 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CBICashAccount20">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIAccountIdentification4Choice"/>
 *         &lt;element name="Tp" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBICashAccountType2" minOccurs="0"/>
 *         &lt;element name="Ccy" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}ActiveOrHistoricCurrencyCode"/>
 *         &lt;element name="Nm" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max70Text" minOccurs="0"/>
 *         &lt;element name="Ownr" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIPartyIdentification32_1"/>
 *         &lt;element name="Svcr" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIBranchAndFinancialInstitutionIdentification4" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CBICashAccount20", propOrder = {
    "id",
    "tp",
    "ccy",
    "nm",
    "ownr",
    "svcr"
})
public class CBICashAccount20 {

    @XmlElement(name = "Id", required = true)
    protected CBIAccountIdentification4Choice id;
    @XmlElement(name = "Tp")
    protected CBICashAccountType2 tp;
    @XmlElement(name = "Ccy", required = true)
    protected String ccy;
    @XmlElement(name = "Nm")
    protected String nm;
    @XmlElement(name = "Ownr", required = true)
    protected CBIPartyIdentification321 ownr;
    @XmlElement(name = "Svcr")
    protected CBIBranchAndFinancialInstitutionIdentification4 svcr;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link CBIAccountIdentification4Choice }
     *     
     */
    public CBIAccountIdentification4Choice getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIAccountIdentification4Choice }
     *     
     */
    public void setId(CBIAccountIdentification4Choice value) {
        this.id = value;
    }

    /**
     * Gets the value of the tp property.
     * 
     * @return
     *     possible object is
     *     {@link CBICashAccountType2 }
     *     
     */
    public CBICashAccountType2 getTp() {
        return tp;
    }

    /**
     * Sets the value of the tp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBICashAccountType2 }
     *     
     */
    public void setTp(CBICashAccountType2 value) {
        this.tp = value;
    }

    /**
     * Gets the value of the ccy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * Sets the value of the ccy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcy(String value) {
        this.ccy = value;
    }

    /**
     * Gets the value of the nm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNm() {
        return nm;
    }

    /**
     * Sets the value of the nm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNm(String value) {
        this.nm = value;
    }

    /**
     * Gets the value of the ownr property.
     * 
     * @return
     *     possible object is
     *     {@link CBIPartyIdentification321 }
     *     
     */
    public CBIPartyIdentification321 getOwnr() {
        return ownr;
    }

    /**
     * Sets the value of the ownr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIPartyIdentification321 }
     *     
     */
    public void setOwnr(CBIPartyIdentification321 value) {
        this.ownr = value;
    }

    /**
     * Gets the value of the svcr property.
     * 
     * @return
     *     possible object is
     *     {@link CBIBranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public CBIBranchAndFinancialInstitutionIdentification4 getSvcr() {
        return svcr;
    }

    /**
     * Sets the value of the svcr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIBranchAndFinancialInstitutionIdentification4 }
     *     
     */
    public void setSvcr(CBIBranchAndFinancialInstitutionIdentification4 value) {
        this.svcr = value;
    }

}
