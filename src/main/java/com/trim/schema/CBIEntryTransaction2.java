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
 * <p>Java class for CBIEntryTransaction2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CBIEntryTransaction2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Refs" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBITransactionReferences2" minOccurs="0"/>
 *         &lt;element name="AmtDtls" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIAmountAndCurrencyExchange3" minOccurs="0"/>
 *         &lt;element name="RltdPties" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBITransactionParty2" minOccurs="0"/>
 *         &lt;element name="RltdAgts" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBITransactionAgents2" minOccurs="0"/>
 *         &lt;element name="Purp" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Purpose2Choice" minOccurs="0"/>
 *         &lt;element name="RmtInf" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIRemittanceInformation5" minOccurs="0"/>
 *         &lt;element name="RtrInf" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}CBIReturnReasonInformation10" minOccurs="0"/>
 *         &lt;element name="AddtlTxInf" type="{urn:CBI:xsd:CBIDlyStmtReqLogMsg.00.01.02}Max500Text" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CBIEntryTransaction2", propOrder = {
    "refs",
    "amtDtls",
    "rltdPties",
    "rltdAgts",
    "purp",
    "rmtInf",
    "rtrInf",
    "addtlTxInf"
})
public class CBIEntryTransaction2 {

    @XmlElement(name = "Refs")
    protected CBITransactionReferences2 refs;
    @XmlElement(name = "AmtDtls")
    protected CBIAmountAndCurrencyExchange3 amtDtls;
    @XmlElement(name = "RltdPties")
    protected CBITransactionParty2 rltdPties;
    @XmlElement(name = "RltdAgts")
    protected CBITransactionAgents2 rltdAgts;
    @XmlElement(name = "Purp")
    protected Purpose2Choice purp;
    @XmlElement(name = "RmtInf")
    protected CBIRemittanceInformation5 rmtInf;
    @XmlElement(name = "RtrInf")
    protected CBIReturnReasonInformation10 rtrInf;
    @XmlElement(name = "AddtlTxInf")
    protected String addtlTxInf;

    /**
     * Gets the value of the refs property.
     * 
     * @return
     *     possible object is
     *     {@link CBITransactionReferences2 }
     *     
     */
    public CBITransactionReferences2 getRefs() {
        return refs;
    }

    /**
     * Sets the value of the refs property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBITransactionReferences2 }
     *     
     */
    public void setRefs(CBITransactionReferences2 value) {
        this.refs = value;
    }

    /**
     * Gets the value of the amtDtls property.
     * 
     * @return
     *     possible object is
     *     {@link CBIAmountAndCurrencyExchange3 }
     *     
     */
    public CBIAmountAndCurrencyExchange3 getAmtDtls() {
        return amtDtls;
    }

    /**
     * Sets the value of the amtDtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIAmountAndCurrencyExchange3 }
     *     
     */
    public void setAmtDtls(CBIAmountAndCurrencyExchange3 value) {
        this.amtDtls = value;
    }

    /**
     * Gets the value of the rltdPties property.
     * 
     * @return
     *     possible object is
     *     {@link CBITransactionParty2 }
     *     
     */
    public CBITransactionParty2 getRltdPties() {
        return rltdPties;
    }

    /**
     * Sets the value of the rltdPties property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBITransactionParty2 }
     *     
     */
    public void setRltdPties(CBITransactionParty2 value) {
        this.rltdPties = value;
    }

    /**
     * Gets the value of the rltdAgts property.
     * 
     * @return
     *     possible object is
     *     {@link CBITransactionAgents2 }
     *     
     */
    public CBITransactionAgents2 getRltdAgts() {
        return rltdAgts;
    }

    /**
     * Sets the value of the rltdAgts property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBITransactionAgents2 }
     *     
     */
    public void setRltdAgts(CBITransactionAgents2 value) {
        this.rltdAgts = value;
    }

    /**
     * Gets the value of the purp property.
     * 
     * @return
     *     possible object is
     *     {@link Purpose2Choice }
     *     
     */
    public Purpose2Choice getPurp() {
        return purp;
    }

    /**
     * Sets the value of the purp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Purpose2Choice }
     *     
     */
    public void setPurp(Purpose2Choice value) {
        this.purp = value;
    }

    /**
     * Gets the value of the rmtInf property.
     * 
     * @return
     *     possible object is
     *     {@link CBIRemittanceInformation5 }
     *     
     */
    public CBIRemittanceInformation5 getRmtInf() {
        return rmtInf;
    }

    /**
     * Sets the value of the rmtInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIRemittanceInformation5 }
     *     
     */
    public void setRmtInf(CBIRemittanceInformation5 value) {
        this.rmtInf = value;
    }

    /**
     * Gets the value of the rtrInf property.
     * 
     * @return
     *     possible object is
     *     {@link CBIReturnReasonInformation10 }
     *     
     */
    public CBIReturnReasonInformation10 getRtrInf() {
        return rtrInf;
    }

    /**
     * Sets the value of the rtrInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBIReturnReasonInformation10 }
     *     
     */
    public void setRtrInf(CBIReturnReasonInformation10 value) {
        this.rtrInf = value;
    }

    /**
     * Gets the value of the addtlTxInf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddtlTxInf() {
        return addtlTxInf;
    }

    /**
     * Sets the value of the addtlTxInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddtlTxInf(String value) {
        this.addtlTxInf = value;
    }

}
