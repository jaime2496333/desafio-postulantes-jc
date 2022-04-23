package com.fapro.exam.scraping.controller;

import com.fapro.exam.scraping.model.insituciones_financiera;
import com.fapro.exam.scraping.service.ServiceUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    ServiceUrl serviceUrl;

    @GetMapping( "/list")
    public Set<insituciones_financiera> getInstitucionFinanciera() {
        return  serviceUrl.getInformation();
    }

    @GetMapping( "/list/{no}")
    public Set<insituciones_financiera> getInstitucionFinancieraByNo(@PathVariable(value = "no") String v_no) {
        return  serviceUrl.getInformationByNo(v_no);
    }

}