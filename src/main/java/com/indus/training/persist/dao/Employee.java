package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Details;
import com.indus.training.persist.svc.IEmployee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Employee implements IEmployee {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

    public Boolean insertEmployee(Details details) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Check if the employee exists
            Details existingEmployee = entityManager.find(Details.class, details.getEmployeeID());
            if (existingEmployee != null) {
                System.out.println("Employee with ID " + details.getEmployeeID() + " already exists.");
                return false;
            }

            // Insert new employee
            entityManager.persist(details);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public Boolean updateEmployee(Details details) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the existing employee
            Details existingEmployee = entityManager.find(Details.class, details.getEmployeeID());
            if (existingEmployee == null) {
                System.out.println("No record found for employeeID: " + details.getEmployeeID());
                return false;
            }

            // Update employee details
            existingEmployee.setFirstName(details.getFirstName());
            existingEmployee.setLastName(details.getLastName());
            existingEmployee.setEmployeeSalary(details.getEmployeeSalary());
            existingEmployee.setEmployeeJobRole(details.getEmployeeJobRole());
            existingEmployee.setNumberOfWorkingHours(details.getNumberOfWorkingHours());

            entityManager.merge(existingEmployee);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public Details fetchEmployee(String employeeID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Fetch employee by ID
            return entityManager.find(Details.class, employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Boolean deleteEmployee(String employeeID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the employee to delete
            Details existingEmployee = entityManager.find(Details.class, employeeID);
            if (existingEmployee == null) {
                System.out.println("No record found for employeeID: " + employeeID);
                return false;
            }

            entityManager.remove(existingEmployee);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}

