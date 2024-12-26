package com.web_filtering.client.filter;

import com.web_filtering.client.dao.AddressBookRepo;
import com.web_filtering.client.dao.entity.AddressBookEntity;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
@Component
public class IncommingRequestValidationFilter implements Filter {

    private final AddressBookRepo addressBookRepo;


    private Set<String> blocked_ip = new HashSet();

    public IncommingRequestValidationFilter(AddressBookRepo addressBookRepo) {

        this.addressBookRepo = addressBookRepo;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;



        if (blocked_ip.isEmpty()) {
            List<AddressBookEntity> list = addressBookRepo.getAllHostAddress();
            list.forEach(buffer -> {
                blocked_ip.add(buffer.getHostIP());
            });
        }

        if (!blocked_ip.isEmpty() && !blocked_ip.contains(request.getRemoteAddr())) {
            AddressBookEntity addressBookEntity = addressBookRepo.getHostAddressByHostIp(request.getRemoteAddr());
            if (addressBookEntity!=null && addressBookEntity.getHostIP().equals(request.getRemoteAddr())) {
                blocked_ip.add(request.getRemoteAddr());
            }
        }

        if (blocked_ip.contains(request.getRemoteAddr())) {
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/restricted");
            dispatcher.forward(httpRequest, httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }
}
