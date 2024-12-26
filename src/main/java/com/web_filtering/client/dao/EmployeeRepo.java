package com.web_filtering.client.dao;

import com.web_filtering.client.dao.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
    /*Skeleton interface doesn't have any methods*/

}
