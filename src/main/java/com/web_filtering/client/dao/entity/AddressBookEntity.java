package com.web_filtering.client.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "ADDRESS_BOOK", name = "BLOCK_LIST")
public class AddressBookEntity {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Setter
    @Getter
    @Column(name = "hostip")
    private String hostIP;

    @Setter
    @Getter
    private String status;

    @Setter
    @Getter
    private LocalDate updatedDate;

}
