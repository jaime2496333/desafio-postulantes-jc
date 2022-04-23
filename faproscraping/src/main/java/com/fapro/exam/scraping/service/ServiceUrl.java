package com.fapro.exam.scraping.service;

import java.util.Set;
import com.fapro.exam.scraping.model.insituciones_financiera;

public interface ServiceUrl {
    
    Set<insituciones_financiera> getInformation();


    Set<insituciones_financiera> getInformationByNo(String v_no);
    
}
