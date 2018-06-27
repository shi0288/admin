
package com.xyauto.qa.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryEmployeeByCnNameResult" type="{http://tempuri.org/}ArrayOfEmployee" minOccurs="0"/>
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
    "queryEmployeeByCnNameResult"
})
@XmlRootElement(name = "QueryEmployeeByCnNameResponse")
public class QueryEmployeeByCnNameResponse {

    @XmlElement(name = "QueryEmployeeByCnNameResult")
    protected ArrayOfEmployee queryEmployeeByCnNameResult;

    /**
     * 获取queryEmployeeByCnNameResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public ArrayOfEmployee getQueryEmployeeByCnNameResult() {
        return queryEmployeeByCnNameResult;
    }

    /**
     * 设置queryEmployeeByCnNameResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public void setQueryEmployeeByCnNameResult(ArrayOfEmployee value) {
        this.queryEmployeeByCnNameResult = value;
    }

}
