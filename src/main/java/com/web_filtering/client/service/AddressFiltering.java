package com.web_filtering.client.service;

import com.web_filtering.client.dao.entity.AddressBookEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AddressFiltering {

    public boolean isValidateHost(HttpServletRequest httpServletRequest);

    public String addHostIPStatus(AddressBookEntity addressBookEntity);

    public int createHostRecord(AddressBookEntity addressBookEntity);

}
