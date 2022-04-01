package com.timofeykibardin.spring.mvc_hibernate_aop.dao;

import com.timofeykibardin.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {


    @Autowired //прописываем зависимость от session factory в appContext
    private SessionFactory sessionFactory;

    @Override
    //@Transactional //Спринг закрывает и открывает транзакцию самостоятельно
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        /*List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
                .getResultList();*/

        //Получаем запрос
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        //Выполняем запрос и результат заносим в лист
        return query.getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

        //Если id работника = 0, то будет создан новый. Если не равен 0, то будет изменение параметров работника
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id =:employeeId"); //:= - вместо employeeId пишем параметр
        query.setParameter("employeeId", id); //Устанавливаем параметр
        query.executeUpdate(); //Отвечает за апдейт и делит операции
    }


}
