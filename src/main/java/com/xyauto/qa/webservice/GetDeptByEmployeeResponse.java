
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
 *         &lt;element name="GetDeptByEmployeeResult" type="{http://tempuri.org/}Department" minOccurs="0"/>
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
    "getDeptByEmployeeResult"
})
@XmlRootElement(name = "GetDeptByEmployeeResponse")
public class GetDeptByEmployeeResponse {

    @XmlElement(name = "GetDeptByEmployeeResult")
    protected Department getDeptByEmployeeResult;

    /**
     * ��ȡgetDeptByEmployeeResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getGetDeptByEmployeeResult() {
        return getDeptByEmployeeResult;
    }

    /**
     * ����getDeptByEmployeeResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setGetDeptByEmployeeResult(Department value) {
        this.getDeptByEmployeeResult = value;
    }

}
