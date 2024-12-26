package com.web_filtering.client.dao.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMP_DATA", schema = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Setter
    @Getter
    private int id;

    public EmployeeEntity(String empNameFirst,
                          String empNameLast,
                          String empUserName,
                          String empBaseLocation
    ) {
        this.empNameFirst = empNameFirst;
        this.empNameLast = empNameLast;
        this.empUserName = empUserName;
        this.empBaseLocation = empBaseLocation;
    }

    @Column(name = "EMP_NAME_FIRST")
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String empNameFirst;

    @Column(name = "EMP_NAME_LAST")
    @Setter
    @Getter
    private String empNameLast;

    @Column(name = "EMP_USER_NAME")
    @Setter
    @Getter
    private String empUserName;

    @Column(name = "EMP_BASE_LOCATION")
    @Setter
    @Getter
    private String empBaseLocation;
    
}
