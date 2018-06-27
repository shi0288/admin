package com.xyauto.qa.service.impl;

import com.xyauto.qa.webservice.EmployeeService;
import com.xyauto.qa.webservice.EmployeeServiceSoap;
import com.xyauto.qa.webservice.LoginResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiqm on 2017/3/23.
 */
public class AdminServiceImplTest {

    @Test
    public void testLogin() throws Exception {

        EmployeeService employeeService = new EmployeeService();
        EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
        LoginResult loginResult = employeeServiceSoap.login("shiqm", "969.FAc");
        System.out.println(loginResult.value());

    }
}