
package com.xyauto.qa.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="deptId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="includeAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "deptId",
    "includeAll"
})
@XmlRootElement(name = "QuerySubDept")
public class QuerySubDept {

    protected int deptId;
    protected boolean includeAll;

    /**
     * ��ȡdeptId���Ե�ֵ��
     * 
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * ����deptId���Ե�ֵ��
     * 
     */
    public void setDeptId(int value) {
        this.deptId = value;
    }

    /**
     * ��ȡincludeAll���Ե�ֵ��
     * 
     */
    public boolean isIncludeAll() {
        return includeAll;
    }

    /**
     * ����includeAll���Ե�ֵ��
     * 
     */
    public void setIncludeAll(boolean value) {
        this.includeAll = value;
    }

}
