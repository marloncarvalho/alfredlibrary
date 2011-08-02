//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.07.28 at 04:08:51 PM BRT 
//


package org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ambiente" type="{cnet_consultamatserv}ambienteTipo"/>
 *         &lt;element name="sistema" type="{cnet_consultamatserv}stringTam15Tipo"/>
 *         &lt;element name="cpf" type="{cnet_consultamatserv}numTam11Tipo"/>
 *         &lt;element name="senha" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acao" type="{cnet_consultamatserv}acaoTipo"/>
 *         &lt;element name="codigo_item" type="{cnet_consultamatserv}numTam9TipoNulo"/>
 *         &lt;element name="radical1" type="{cnet_consultamatserv}stringTam25TipoNulo"/>
 *         &lt;element name="radical2" type="{cnet_consultamatserv}stringTam25TipoNulo"/>
 *         &lt;element name="radical3" type="{cnet_consultamatserv}stringTam25TipoNulo"/>
 *         &lt;element name="sustentavel" type="{cnet_consultamatserv}sustentavelTipoNulo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ambiente",
    "sistema",
    "cpf",
    "senha",
    "acao",
    "codigoItem",
    "radical1",
    "radical2",
    "radical3",
    "sustentavel"
})
@XmlRootElement(name = "cnet")
public class Cnet {

    @XmlElement(required = true)
    protected String ambiente;
    @XmlElement(required = true)
    protected String sistema;
    protected long cpf;
    @XmlElement(required = true)
    protected String senha;
    @XmlElement(required = true)
    protected String acao;
    @XmlElement(name = "codigo_item", required = true, nillable = true)
    protected String codigoItem;
    @XmlElement(required = true, nillable = true)
    protected String radical1;
    @XmlElement(required = true, nillable = true)
    protected String radical2;
    @XmlElement(required = true, nillable = true)
    protected String radical3;
    @XmlElement(required = true)
    protected String sustentavel;

    /**
     * Gets the value of the ambiente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * Sets the value of the ambiente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbiente(String value) {
        this.ambiente = value;
    }

    /**
     * Gets the value of the sistema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * Sets the value of the sistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistema(String value) {
        this.sistema = value;
    }

    /**
     * Gets the value of the cpf property.
     * 
     */
    public long getCpf() {
        return cpf;
    }

    /**
     * Sets the value of the cpf property.
     * 
     */
    public void setCpf(long value) {
        this.cpf = value;
    }

    /**
     * Gets the value of the senha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Sets the value of the senha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenha(String value) {
        this.senha = value;
    }

    /**
     * Gets the value of the acao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcao() {
        return acao;
    }

    /**
     * Sets the value of the acao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcao(String value) {
        this.acao = value;
    }

    /**
     * Gets the value of the codigoItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoItem() {
        return codigoItem;
    }

    /**
     * Sets the value of the codigoItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoItem(String value) {
        this.codigoItem = value;
    }

    /**
     * Gets the value of the radical1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadical1() {
        return radical1;
    }

    /**
     * Sets the value of the radical1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadical1(String value) {
        this.radical1 = value;
    }

    /**
     * Gets the value of the radical2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadical2() {
        return radical2;
    }

    /**
     * Sets the value of the radical2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadical2(String value) {
        this.radical2 = value;
    }

    /**
     * Gets the value of the radical3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadical3() {
        return radical3;
    }

    /**
     * Sets the value of the radical3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadical3(String value) {
        this.radical3 = value;
    }

    /**
     * Gets the value of the sustentavel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSustentavel() {
        return sustentavel;
    }

    /**
     * Sets the value of the sustentavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSustentavel(String value) {
        this.sustentavel = value;
    }

}
