package com.web_filtering.client.service;

import com.web_filtering.client.dao.AddressBookRepo;
import com.web_filtering.client.dao.entity.AddressBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;


@Service("AddressFilteringImpl")
public class AddressFilteringImpl implements AddressFiltering {

    @Autowired
    AddressBookRepo addressBookRepo;

    @Override
    public boolean isValidateHost(HttpServletRequest httpServletRequest) {
        return false;
    }

    @Override
    public int createHostRecord(AddressBookEntity addressBookEntity) {
        addressBookEntity.setUpdatedDate(LocalDate.now());
        AddressBookEntity addressBookEntity1 =
                addressBookRepo.getHostAddressByHostIp(addressBookEntity.getHostIP());
        return addressBookEntity1 == null ? addressBookRepo.save(addressBookEntity).getId() : 0;
    }

    @Override
    public String addHostIPStatus(AddressBookEntity addressBookEntity) {

        return null;
    }

    public List<AddressBookEntity> getAllHostAddress() {
        return addressBookRepo.getAllHostAddress();
    }

    public AddressBookEntity getHostAddressByHostIp(String hostIp) {
        return addressBookRepo.getHostAddressByHostIp(hostIp);
    }


}
