package com.nagarro.employeeapi.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.employeeapi.dao.EmployeeDao;
import com.nagarro.employeeapi.model.Employee;

@RestController
@RequestMapping("/employee-api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * @return List of employees
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    /**
     * @param id
     * @return employee by id
     */
    @GetMapping
    @RequestMapping("{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeDao.getOne(id);
    }

    /**
     * @param employee
     * @return save the employee in database table
     */
    @PostMapping
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeDao.saveAndFlush(employee);
    }

    /**
     * Delete an employee
     * 
     * @param id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable long id) {
        employeeDao.deleteById(id);
    }

    /**
     * Update an employee
     * 
     * @param id
     * @param employee
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeDao.saveAndFlush(employee);
    }
}
