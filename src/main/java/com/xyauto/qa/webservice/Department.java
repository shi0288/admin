
package com.xyauto.qa.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Department complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Department">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DepartmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ParentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FullPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Department", propOrder = {
    "departmentId",
    "parentId",
    "name",
    "shortName",
    "enName",
    "description",
    "fullPath",
    "status"
})
public class Department {

    @XmlElement(name = "DepartmentId")
    protected int departmentId;
    @XmlElement(name = "ParentId")
    protected int parentId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "ShortName")
    protected String shortName;
    @XmlElement(name = "EnName")
    protected String enName;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "FullPath")
    protected String fullPath;
    @XmlElement(name = "Status")
    protected int status;

    /**
     * ��ȡdepartmentId���Ե�ֵ��
     * 
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * ����departmentId���Ե�ֵ��
     * 
     */
    public void setDepartmentId(int value) {
        this.departmentId = value;
    }

    /**
     * ��ȡparentId���Ե�ֵ��
     * 
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * ����parentId���Ե�ֵ��
     * 
     */
    public void setParentId(int value) {
        this.parentId = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡshortName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * ����shortName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * ��ȡenName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnName() {
        return enName;
    }

    /**
     * ����enName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnName(String value) {
        this.enName = value;
    }

    /**
     * ��ȡdescription���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����description���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * ��ȡfullPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * ����fullPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullPath(String value) {
        this.fullPath = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}
