package com.techmind.project_enterprise.service;

import com.techmind.project_enterprise.model.Employee;
import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Profile;

import java.util.List;
import java.util.Optional;

public interface IEnterpriseService {

    List<Enterprise> getAllEnterprise();
    Enterprise saveEnterprise (Enterprise enterprise);
    Enterprise getEnterpriseById(Long id);
    Enterprise updateEnterprise(Enterprise enterprise);
    void deleteEnterpriseById(Long id);
     boolean existSById(Long id);
     Optional<Enterprise>getOne(Long id);

}
