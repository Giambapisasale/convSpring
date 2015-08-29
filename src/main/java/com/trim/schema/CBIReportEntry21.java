//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.28 at 10:18:42 PM CEST 
//


package com.trim.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CBIReportEntry2_1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CBIReportEntry2_1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NtryRef" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max35Text"/>
 *         &lt;element name="Amt" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}ActiveOrHistoricCurrencyAndAmount"/>
 *         &lt;element name="CdtDbtInd" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CreditDebitCode"/>
 *         &lt;element name="Sts" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIEntryStatus2Code"/>
 *         &lt;element name="BookgDt" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}DateAndDateTimeChoice"/>
 *         &lt;element name="ValDt" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}DateAndDateTimeChoice"/>
 *         &lt;element name="AcctSvcrRef" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max35Text" minOccurs="0"/>
 *         &lt;element name="Avlbty" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CashBalanceAvailability2" minOccurs="0"/>
 *         &lt;element name="BkTxCd" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}BankTransactionCodeStructure4"/>
 *         &lt;element name="AmtDtls" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}AmountAndCurrencyExchange3" minOccurs="0"/>
 *         &lt;element name="TechInptChanl" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}TechnicalInputChannel1Choice" minOccurs="0"/>
 *         &lt;element name="NtryDtls" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIEntryDetails1" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AddtlNtryInf" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max500Text" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CBIReportEntry2_1", propOrder = {
    "ntryRef",
    "amt",
    "cdtDbtInd",
    "sts",
    "bookgDt",
    "valDt",
    "acctSvcrRef",
    "avlbty",
    "bkTxCd",
    "amtDtls",
    "techInptChanl",
    "ntryDtls",
    "addtlNtryInf"
})
public class CBIReportEntry21 {

    @XmlElement(name = "NtryRef", required = true)
    protected String ntryRef;
    @XmlElement(name = "Amt", required = true)
    protected ActiveOrHistoricCurrencyAndAmount amt;
    @XmlElement(name = "CdtDbtInd", required = true)
    protected CreditDebitCode cdtDbtInd;
    @XmlElement(name = "Sts", required = true)
    protected CBIEntryStatus2Code sts;
    @XmlElement(name = "BookgDt", required = true)
    protected DateAndDateTimeChoice bookgDt;
    @XmlElement(name = "ValDt", required = true)
    protected DateAndDateTimeChoice valDt;
    @XmlElement(name = "AcctSvcrRef")
    protected String acctSvcrRef;
    @XmlElement(name = "Avlbty")
    protected CashBalanceAvailability2 avlbty;
    @XmlElement(name = "BkTxCd", required = true)
    protected BankTransactionCodeStructure4 bkTxCd;
    @XmlElement(name = "AmtDtls")
    protected AmountAndCurrencyExchange3 amtDtls;
    @XmlElement(name = "TechInptChanl")
    protected TechnicalInputChannel1Choice techInptChanl;
    @XmlElement(name = "NtryDtls")
    protected List<CBIEntryDetails1> ntryDtls;
    @XmlElement(name = "AddtlNtryInf")
    protected String addtlNtryInf;

    /**
     * Gets the value of the ntryRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNtryRef() {
        return ntryRef;
    }

    /**
     * Sets the value of the ntryRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNtryRef(String value) {
        this.ntryRef = value;
    }

    /**
     * Gets the value of the amt property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveOrHistoricCurrencyAndAmount }
     *     
     */
    public ActiveOrHistoricCurrencyAndAmount getAmt() {
        return amt;
    }

    /**
     * Sets the value of the amt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveOrHistoricCurrencyAndAmount }
     *     
     */
    public void setAmt(ActiveOrHistoricCurrencyAndAmount value) {
        this.amt = value;
    }

    /**
     * Gets the value of the cdtDbtInd property.
     * 
     * @return
     *     possible object is
     *     {@link CreditDebitCode }
     *     
     */
    public CreditDebitCode getCdtDbtInd() {
        return cdtDbtInd;
    }

    /**
     * Sets the value of the cdtDbtInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditDebitCode }
     *     
     */
    public void setCdtDbtInd(CreditDebitCode value) {
        this.cdtDbtInd = value;
    }

    /**
     * Gets the value of the sts property.
     * 
     * @return
     *     possible object is
     *     {@link CBIEntryStatus2Code }
     *     
     */
    public CBIEntryStatus2Code getSts() {
        return sts;
    }

    /**
     * Sets the value of the sts property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIEntryStatus2Code }
     *     
     */
    public void setSts(CBIEntryStatus2Code value) {
        this.sts = value;
    }

    /**
     * Gets the value of the bookgDt property.
     * 
     * @return
     *     possible object is
     *     {@link DateAndDateTimeChoice }
     *     
     */
    public DateAndDateTimeChoice getBookgDt() {
        return bookgDt;
    }

    /**
     * Sets the value of the bookgDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateAndDateTimeChoice }
     *     
     */
    public void setBookgDt(DateAndDateTimeChoice value) {
        this.bookgDt = value;
    }

    /**
     * Gets the value of the valDt property.
     * 
     * @return
     *     possible object is
     *     {@link DateAndDateTimeChoice }
     *     
     */
    public DateAndDateTimeChoice getValDt() {
        return valDt;
    }

    /**
     * Sets the value of the valDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateAndDateTimeChoice }
     *     
     */
    public void setValDt(DateAndDateTimeChoice value) {
        this.valDt = value;
    }

    /**
     * Gets the value of the acctSvcrRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSvcrRef() {
        return acctSvcrRef;
    }

    /**
     * Sets the value of the acctSvcrRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSvcrRef(String value) {
        this.acctSvcrRef = value;
    }

    /**
     * Gets the value of the avlbty property.
     * 
     * @return
     *     possible object is
     *     {@link CashBalanceAvailability2 }
     *     
     */
    public CashBalanceAvailability2 getAvlbty() {
        return avlbty;
    }

    /**
     * Sets the value of the avlbty property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashBalanceAvailability2 }
     *     
     */
    public void setAvlbty(CashBalanceAvailability2 value) {
        this.avlbty = value;
    }

    /**
     * Gets the value of the bkTxCd property.
     * 
     * @return
     *     possible object is
     *     {@link BankTransactionCodeStructure4 }
     *     
     */
    public BankTransactionCodeStructure4 getBkTxCd() {
        return bkTxCd;
    }

    /**
     * Sets the value of the bkTxCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankTransactionCodeStructure4 }
     *     
     */
    public void setBkTxCd(BankTransactionCodeStructure4 value) {
        this.bkTxCd = value;
    }

    /**
     * Gets the value of the amtDtls property.
     * 
     * @return
     *     possible object is
     *     {@link AmountAndCurrencyExchange3 }
     *     
     */
    public AmountAndCurrencyExchange3 getAmtDtls() {
        return amtDtls;
    }

    /**
     * Sets the value of the amtDtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountAndCurrencyExchange3 }
     *     
     */
    public void setAmtDtls(AmountAndCurrencyExchange3 value) {
        this.amtDtls = value;
    }

    /**
     * Gets the value of the techInptChanl property.
     * 
     * @return
     *     possible object is
     *     {@link TechnicalInputChannel1Choice }
     *     
     */
    public TechnicalInputChannel1Choice getTechInptChanl() {
        return techInptChanl;
    }

    /**
     * Sets the value of the techInptChanl property.
     * 
     * @param value
     *     allowed object is
     *     {@link TechnicalInputChannel1Choice }
     *     
     */
    public void setTechInptChanl(TechnicalInputChannel1Choice value) {
        this.techInptChanl = value;
    }

    /**
     * Gets the value of the ntryDtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ntryDtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNtryDtls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBIEntryDetails1 }
     * 
     * 
     */
    public List<CBIEntryDetails1> getNtryDtls() {
        if (ntryDtls == null) {
            ntryDtls = new ArrayList<CBIEntryDetails1>();
        }
        return this.ntryDtls;
    }

    /**
     * Gets the value of the addtlNtryInf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddtlNtryInf() {
        return addtlNtryInf;
    }

    /**
     * Sets the value of the addtlNtryInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddtlNtryInf(String value) {
        this.addtlNtryInf = value;
    }

}
