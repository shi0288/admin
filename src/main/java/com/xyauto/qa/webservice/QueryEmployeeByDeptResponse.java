
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
 *         &lt;element name="QueryEmployeeByDeptResult" type="{http://tempuri.org/}ArrayOfEmployee" minOccurs="0"/>
 *         &lt;element name="employeeCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "queryEmployeeByDeptResult",
    "employeeCount"
})
@XmlRootElement(name = "QueryEmployeeByDeptResponse")
public class QueryEmployeeByDeptResponse {

    @XmlElement(name = "QueryEmployeeByDeptResult")
    protected ArrayOfEmployee queryEmployeeByDeptResult;
    protected int employeeCount;

    /**
     * ��ȡqueryEmployeeByDeptResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public ArrayOfEmployee getQueryEmployeeByDeptResult() {
        return queryEmployeeByDeptResult;
    }

    /**
     * ����queryEmployeeByDeptResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public void setQueryEmployeeByDeptResult(ArrayOfEmployee value) {
        this.queryEmployeeByDeptResult = value;
    }

    /**
     * ��ȡemployeeCount���Ե�ֵ��
     * 
     */
    public int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * ����employeeCount���Ե�ֵ��
     * 
     */
    public void setEmployeeCount(int value) {
        this.employeeCount = value;
    }

}
