package com.web_filtering.client.controllers;

import com.web_filtering.client.dao.entity.EmployeeEntity;
import com.web_filtering.client.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/loadData")
    public String loadEmpSampleData() {
        employeeService.storeSampleData();
        return "empfinder";
    }

    @GetMapping("/getemp")
    public String getEmpHomepage() {
        return "empfinder";
    }

    @GetMapping("/empId")
    public String searchEmployeeById(@RequestParam("empUser") String empId, Model model) {
        List<EmployeeEntity> employeeList = employeeService.getEmployeeById(empId);
        model.addAttribute("empResp", !employeeList.isEmpty() ? employeeList : null);
        return "empdataresults";
    }

    @DeleteMapping("/empdelete")
    public ResponseEntity<String> deleteEmpById(@RequestParam("empUser") String empId) {
        boolean isDeleted = employeeService.deleteEmpById(empId);
        if (isDeleted) {
            return ResponseEntity.ok("Record has been deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found with the given username.");
        }
    }


    @GetMapping("/del")
    public String getDeletePage() {
        return "empdelete";
    }

}
