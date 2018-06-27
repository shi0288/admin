
package com.xyauto.qa.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmployeeService", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://hr.oa.xingyuanauto.com/webservice/EmployeeService.asmx?wsdl")
public class EmployeeService
    extends Service
{

    private final static URL EMPLOYEESERVICE_WSDL_LOCATION;
    private final static WebServiceException EMPLOYEESERVICE_EXCEPTION;
    private final static QName EMPLOYEESERVICE_QNAME = new QName("http://tempuri.org/", "EmployeeService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://hr.oa.xingyuanauto.com/webservice/EmployeeService.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMPLOYEESERVICE_WSDL_LOCATION = url;
        EMPLOYEESERVICE_EXCEPTION = e;
    }

    public EmployeeService() {
        super(__getWsdlLocation(), EMPLOYEESERVICE_QNAME);
    }

    public EmployeeService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMPLOYEESERVICE_QNAME, features);
    }

    public EmployeeService(URL wsdlLocation) {
        super(wsdlLocation, EMPLOYEESERVICE_QNAME);
    }

    public EmployeeService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMPLOYEESERVICE_QNAME, features);
    }

    public EmployeeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmployeeService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmployeeServiceSoap
     */
    @WebEndpoint(name = "EmployeeServiceSoap")
    public EmployeeServiceSoap getEmployeeServiceSoap() {
        return super.getPort(new QName("http://tempuri.org/", "EmployeeServiceSoap"), EmployeeServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmployeeServiceSoap
     */
    @WebEndpoint(name = "EmployeeServiceSoap")
    public EmployeeServiceSoap getEmployeeServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "EmployeeServiceSoap"), EmployeeServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMPLOYEESERVICE_EXCEPTION!= null) {
            throw EMPLOYEESERVICE_EXCEPTION;
        }
        return EMPLOYEESERVICE_WSDL_LOCATION;
    }

}
