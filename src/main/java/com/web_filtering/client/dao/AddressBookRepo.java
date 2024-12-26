package com.web_filtering.client.dao;

import com.web_filtering.client.dao.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepo extends JpaRepository<AddressBookEntity, Integer> {

    @Query(value = "SELECT * FROM ADDRESS_BOOK.BLOCK_LIST WHERE hostip IS NOT NULL", nativeQuery = true)
    List<AddressBookEntity> getAllHostAddress();

    @Query(value = "SELECT * FROM ADDRESS_BOOK.BLOCK_LIST WHERE hostip = :hostIP", nativeQuery = true)
    AddressBookEntity getHostAddressByHostIp(@Param("hostIP") String hostIP);


}
