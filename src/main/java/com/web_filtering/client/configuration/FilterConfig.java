package com.web_filtering.client.configuration;

import com.web_filtering.client.dao.AddressBookRepo;
import com.web_filtering.client.filter.IncommingRequestValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final AddressBookRepo addressBookRepo;

    @Autowired
    public FilterConfig(AddressBookRepo addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @Bean
    public FilterRegistrationBean<IncommingRequestValidationFilter> loggingFilter() {
        FilterRegistrationBean<IncommingRequestValidationFilter> registrationBean = new FilterRegistrationBean<>();

        // Manually creating and injecting dependencies into the filter
        IncommingRequestValidationFilter filter = new IncommingRequestValidationFilter(addressBookRepo);

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
