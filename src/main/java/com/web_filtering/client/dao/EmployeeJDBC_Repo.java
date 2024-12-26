package com.web_filtering.client.dao;

import com.web_filtering.client.dao.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeJDBC_Repo {
    /*This class will have all the*/
    @Autowired
    JdbcTemplate jdbcTemplate;

    static String EMP_ID = "ID";
    static String EMP_NAME_FIRST = "EMP_NAME_FIRST";
    static String EMP_NAME_LAST = "EMP_NAME_LAST";
    static String EMP_USER_NAME = "EMP_USER_NAME";
    static String EMP_BASE_LOCATION = "EMP_BASE_LOCATION";

    public List<EmployeeEntity> getEmployeeById(String sqlQueryinput) {
        String sql_employeeById = String.format("SELECT * FROM EMPLOYEE.EMP_DATA WHERE EMP_USER_NAME = '%s'", sqlQueryinput);
        List<EmployeeEntity> employeeEntityList = jdbcTemplate.query(sql_employeeById, (rs, rowNum) -> {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setId(rs.getInt(EMP_ID));
            employee.setEmpNameFirst(rs.getString(EMP_NAME_FIRST));
            employee.setEmpNameLast(rs.getString(EMP_NAME_LAST));
            employee.setEmpUserName(rs.getString(EMP_USER_NAME));
            employee.setEmpBaseLocation(rs.getString(EMP_BASE_LOCATION));
            return employee;
        });

        return employeeEntityList;
    }

    public int getDeleteById(String empId) {
        String sql_deleteById = String.format("DELETE FROM EMPLOYEE.EMP_DATA WHERE EMP_USER_NAME = '%s'", empId);
        String sql_findCount = String.format("SELECT COUNT(*) FROM EMPLOYEE.EMP_DATA WHERE EMP_USER_NAME = ?", empId);
        jdbcTemplate.execute(sql_deleteById);
        int result = jdbcTemplate.queryForObject(sql_findCount, Integer.class, empId);
        return result;
    }

}
