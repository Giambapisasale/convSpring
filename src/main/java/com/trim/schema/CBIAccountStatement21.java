//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.03 at 06:46:19 PM CEST 
//


package com.trim.schema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CBIAccountStatement2_1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CBIAccountStatement2_1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max35Text"/>
 *         &lt;element name="ElctrncSeqNb" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Number"/>
 *         &lt;element name="CreDtTm" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}ISODateTime"/>
 *         &lt;element name="CpyDplctInd" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBICopyDuplicate1Code" minOccurs="0"/>
 *         &lt;element name="Acct" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBICashAccount20"/>
 *         &lt;element name="Bal" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBICashBalance3" maxOccurs="unbounded"/>
 *         &lt;element name="TxsSummry" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBITotalTransactions2" minOccurs="0"/>
 *         &lt;element name="Ntry" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIReportEntry2_1" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AddtlStmtInf" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max500Text" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CBIAccountStatement2_1", propOrder = {
    "id",
    "elctrncSeqNb",
    "creDtTm",
    "cpyDplctInd",
    "acct",
    "bal",
    "txsSummry",
    "ntry",
    "addtlStmtInf"
})
public class CBIAccountStatement21 {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "ElctrncSeqNb", required = true)
    protected BigDecimal elctrncSeqNb;
    @XmlElement(name = "CreDtTm", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date creDtTm;
    @XmlElement(name = "CpyDplctInd")
    protected CBICopyDuplicate1Code cpyDplctInd;
    @XmlElement(name = "Acct", required = true)
    protected CBICashAccount20 acct;
    @XmlElement(name = "Bal", required = true)
    protected List<CBICashBalance3> bal;
    @XmlElement(name = "TxsSummry")
    protected CBITotalTransactions2 txsSummry;
    @XmlElement(name = "Ntry")
    protected List<CBIReportEntry21> ntry;
    @XmlElement(name = "AddtlStmtInf")
    protected String addtlStmtInf;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the elctrncSeqNb property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getElctrncSeqNb() {
        return elctrncSeqNb;
    }

    /**
     * Sets the value of the elctrncSeqNb property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setElctrncSeqNb(BigDecimal value) {
        this.elctrncSeqNb = value;
    }

    /**
     * Gets the value of the creDtTm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCreDtTm() {
        return creDtTm;
    }

    /**
     * Sets the value of the creDtTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreDtTm(Date value) {
        this.creDtTm = value;
    }

    /**
     * Gets the value of the cpyDplctInd property.
     * 
     * @return
     *     possible object is
     *     {@link CBICopyDuplicate1Code }
     *     
     */
    public CBICopyDuplicate1Code getCpyDplctInd() {
        return cpyDplctInd;
    }

    /**
     * Sets the value of the cpyDplctInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBICopyDuplicate1Code }
     *     
     */
    public void setCpyDplctInd(CBICopyDuplicate1Code value) {
        this.cpyDplctInd = value;
    }

    /**
     * Gets the value of the acct property.
     * 
     * @return
     *     possible object is
     *     {@link CBICashAccount20 }
     *     
     */
    public CBICashAccount20 getAcct() {
        return acct;
    }

    /**
     * Sets the value of the acct property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBICashAccount20 }
     *     
     */
    public void setAcct(CBICashAccount20 value) {
        this.acct = value;
    }

    /**
     * Gets the value of the bal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBICashBalance3 }
     * 
     * 
     */
    public List<CBICashBalance3> getBal() {
        if (bal == null) {
            bal = new ArrayList<CBICashBalance3>();
        }
        return this.bal;
    }

    /**
     * Gets the value of the txsSummry property.
     * 
     * @return
     *     possible object is
     *     {@link CBITotalTransactions2 }
     *     
     */
    public CBITotalTransactions2 getTxsSummry() {
        return txsSummry;
    }

    /**
     * Sets the value of the txsSummry property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBITotalTransactions2 }
     *     
     */
    public void setTxsSummry(CBITotalTransactions2 value) {
        this.txsSummry = value;
    }

    /**
     * Gets the value of the ntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNtry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBIReportEntry21 }
     * 
     * 
     */
    public List<CBIReportEntry21> getNtry() {
        if (ntry == null) {
            ntry = new ArrayList<CBIReportEntry21>();
        }
        return this.ntry;
    }

    /**
     * Gets the value of the addtlStmtInf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddtlStmtInf() {
        return addtlStmtInf;
    }

    /**
     * Sets the value of the addtlStmtInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddtlStmtInf(String value) {
        this.addtlStmtInf = value;
    }

}
