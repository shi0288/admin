
package com.xyauto.qa.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QuerySubDeptResult" type="{http://tempuri.org/}ArrayOfDepartment" minOccurs="0"/>
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
    "querySubDeptResult"
})
@XmlRootElement(name = "QuerySubDeptResponse")
public class QuerySubDeptResponse {

    @XmlElement(name = "QuerySubDeptResult")
    protected ArrayOfDepartment querySubDeptResult;

    /**
     * ��ȡquerySubDeptResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDepartment }
     *     
     */
    public ArrayOfDepartment getQuerySubDeptResult() {
        return querySubDeptResult;
    }

    /**
     * ����querySubDeptResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDepartment }
     *     
     */
    public void setQuerySubDeptResult(ArrayOfDepartment value) {
        this.querySubDeptResult = value;
    }

}
