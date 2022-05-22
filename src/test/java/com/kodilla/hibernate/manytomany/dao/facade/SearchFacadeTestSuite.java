package com.kodilla.hibernate.manytomany.dao.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import com.kodilla.hibernate.manytomany.facade.SearchFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchFacadeTestSuite {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private SearchFacade searchFacade;

    @Test
    void testFindCompanyByNameFragment() {
        //Given
        Company softwareMachine = new Company("Software Machine");
        Company dataMasters = new Company("Data Masters");
        Company greyMatter = new Company("Grey Matter");

        companyDao.save(softwareMachine);
        companyDao.save(dataMasters);
        companyDao.save(greyMatter);

        //When
        List<Company> companyNameFragmentQuery = searchFacade.findCompanyByFragmentQuery("aster");
        List<Company> nonexistentCompanyNameFragmentQuery = searchFacade.findCompanyByFragmentQuery("xyzq");

        //Then
        assertEquals(1, companyNameFragmentQuery.size());
        assertEquals(0, nonexistentCompanyNameFragmentQuery.size());
    }

    @Test
    void testFindEmployeeByLastNameFragment() {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarkson = new Employee("Stephanie", "Clarkson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        employeeDao.save(johnSmith);
        employeeDao.save(stephanieClarkson);
        employeeDao.save(lindaKovalsky);

        //When
        List<Employee> employeeLastNameFragmentQuery = searchFacade.findEmployeeByFragmentQuery("arks");
        List<Employee> notEmployedLastNameFragmentQuery = searchFacade.findEmployeeByFragmentQuery("Anonymous");

        //Then
        assertEquals(1, employeeLastNameFragmentQuery.size());
        assertEquals(0, notEmployedLastNameFragmentQuery.size());
    }

    @AfterEach
    void cleanup() {
        companyDao.deleteAll();
        employeeDao.deleteAll();
    }
}