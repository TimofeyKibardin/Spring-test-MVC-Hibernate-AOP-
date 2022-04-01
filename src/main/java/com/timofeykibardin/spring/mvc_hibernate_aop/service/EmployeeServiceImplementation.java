package com.timofeykibardin.spring.mvc_hibernate_aop.service;

import com.timofeykibardin.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.timofeykibardin.spring.mvc_hibernate_aop.dao.EmployeeDAOImplementation;
import com.timofeykibardin.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Класс содержит бизнес-логику. Сервис - соединительное звено между контроллером и DAO
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

}
