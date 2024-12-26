package com.web_filtering.client.service;

import com.web_filtering.client.dao.EmployeeJDBC_Repo;
import com.web_filtering.client.dao.EmployeeRepo;
import com.web_filtering.client.dao.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeJDBC_Repo employeeJDBCRepo;

    @Value("${sql.audit.enable}")
    Boolean sqlAuditEnable = Boolean.FALSE;

    @Value("${sql.query.divider}")
    String queryDivider;
    @Value("${sql.query.comparator}")
    String queryComparator;
    static int ONE = 1;
    static int ZERO = 0;
    static String SINGLE_COLON = "'";
    static String SPACE = " ";


    public void storeSampleData() {

        List<EmployeeEntity> employees = Arrays.asList(
                new EmployeeEntity("John", "Doe", "johndoe", "New York"),
                new EmployeeEntity("Jane", "Smith", "janesmith", "San Francisco"),
                new EmployeeEntity("Michael", "Johnson", "mjohnson", "Los Angeles"),
                new EmployeeEntity("Emily", "Williams", "emilyw", "Chicago"),
                new EmployeeEntity("David", "Brown", "davidb", "Houston"),
                new EmployeeEntity("Sarah", "Jones", "sarahj", "Phoenix"),
                new EmployeeEntity("James", "Miller", "jamesm", "Dallas"),
                new EmployeeEntity("Mary", "Davis", "maryd", "San Diego"),
                new EmployeeEntity("Robert", "Garcia", "robertg", "Seattle"),
                new EmployeeEntity("Linda", "Martinez", "lindam", "Austin"),
                new EmployeeEntity("William", "Hernandez", "williamh", "Denver"),
                new EmployeeEntity("Elizabeth", "Lopez", "elizabethl", "Boston"),
                new EmployeeEntity("Charles", "Gonzalez", "charlesg", "Miami"),
                new EmployeeEntity("Daniel", "Perez", "danielp", "Atlanta"),
                new EmployeeEntity("Olivia", "Wilson", "oliviaw", "Orlando"),
                new EmployeeEntity("Matthew", "Anderson", "matthewa", "Tampa"),
                new EmployeeEntity("Sophia", "Thomas", "sophiat", "Las Vegas"),
                new EmployeeEntity("Benjamin", "Jackson", "benjaminj", "Detroit"),
                new EmployeeEntity("Ava", "White", "avaw", "Charlotte")
        );

        employeeRepo.saveAll(employees);
        System.out.print("All employees data has loaded into the DB");
    }


    public List<EmployeeEntity> getEmployeeById(String empUserId) throws IllegalArgumentException {

        if (sqlAuditEnable && isPotentialSqlInjection(empUserId) && isInvalidInput(empUserId)) {
            throw new IllegalArgumentException("The entered input has the unexpected values");
        }

        return employeeJDBCRepo.getEmployeeById(empUserId);
    }

    public Boolean deleteEmpById(String empUserId) throws IllegalArgumentException {
        int result;
        if (sqlAuditEnable && isPotentialSqlInjection(empUserId) && isInvalidInput(empUserId)) {
            throw new IllegalArgumentException("The entered input has the unexpected values");
        } else {
            result = employeeJDBCRepo.getDeleteById(empUserId);
            if (result >= ZERO) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    //audit the input that are receive the suspect values
    private final String[] SQL_INJECTION_PATTERNS = {
            "--",               // Single-line comment
            ";",                // Command separator
            "'",                // Single quote (string delimiter)
            "\"",               // Double quote (string delimiter)
            "/*",               // Start of multi-line comment
            "*/",               // End of multi-line comment
            "union",            // UNION keyword (commonly used in injections)
            "or",               // OR condition (often used to bypass login)
            "and",              // AND condition (often used to bypass login)
            "xor",              // XOR operator (sometimes used in injections)
            "exec",             // EXEC command (executes a string as SQL code)
            "eval",             // EVAL function (may be used to execute SQL code)
            "benchmark",        // BENCHMARK function (used in timing attacks)
            "sleep",            // Sleep function (used in time-based SQL injections)
            "/*!",              // MySQL specific comment-based injection
    };

    public boolean isPotentialSqlInjection(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        // Check for suspicious patterns
        for (String pattern : SQL_INJECTION_PATTERNS) {
            if (query.toLowerCase().contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidInput(String inputForQuery) {
        String filteredInput = inputForQuery.replaceAll(SINGLE_COLON, SPACE);
        String[] injectedValues = filteredInput.split(queryDivider)[ONE].split(queryComparator);
        if (injectedValues != null &&
                (injectedValues[ZERO].trim()).equals(injectedValues[ONE].trim())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}
