package com.web_filtering.client.controllers;

import com.web_filtering.client.dao.entity.AddressBookEntity;
import com.web_filtering.client.service.AddressFilteringImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AddressRequestControllers {

    @Autowired
    @Qualifier("AddressFilteringImpl")
    AddressFilteringImpl addressFilteringImpl;

    @GetMapping("/restricted")
    public String getlimitedAccess() {

        return "restricted";
    }

    @GetMapping("/ipupdate")
    public String getIpUpdatePage() {
        return "ipupdate";
    }

    @PostMapping("/ipupdate")
    public String addIPStatus(@ModelAttribute AddressBookEntity addressBookEntity, Model model) {
        int updateStatus = addressFilteringImpl.createHostRecord(addressBookEntity);

        if (updateStatus != 0) {
            model.addAttribute("hostId", updateStatus);
        } else {
            model.addAttribute("hostId", "Error: Unable to update IP");
        }
        return "ipupdate";
    }

    @PostMapping("/getHostId")
    public String getBlocklistedIp(@RequestParam("hostIp") String hostIp, Model model) {
        AddressBookEntity addressBookEntity = addressFilteringImpl.getHostAddressByHostIp(hostIp);
        model.addAttribute("addressBookEntity", addressBookEntity);
        return "hostresult";
    }

    @GetMapping("/getHostId")
    public String getBlocklistedIp() {
        return "hostresult";
    }


}
