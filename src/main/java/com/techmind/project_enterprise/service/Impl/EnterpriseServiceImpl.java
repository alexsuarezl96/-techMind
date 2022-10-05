package com.techmind.project_enterprise.service.Impl;

import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.repository.IEnterpriseRepository;
import com.techmind.project_enterprise.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImpl  implements IEnterpriseService {
@Autowired
private  IEnterpriseRepository repoenterprise;

    @Override
    public List<Enterprise> getAllEnterprise() {
        return repoenterprise.findAll();
    }

    @Override
    public Enterprise saveEnterprise(Enterprise enterprise) {

        return repoenterprise.save(enterprise);
    }



    @Override
    public Enterprise getEnterpriseById(Long id) {
        return repoenterprise.findById(id).get();
    }


    @Override
    public Enterprise updateEnterprise(Enterprise enterprise) {

        return repoenterprise.save(enterprise);
    }

    @Override
    public void deleteEnterpriseById(Long id) {
        repoenterprise.deleteById(id);

    }

    @Override
    public boolean existSById(Long id) {

        return repoenterprise.existsById(id);
    }


    @Override
    public Optional<Enterprise> getOne(Long id) {
        return repoenterprise.findById(id);
    }


}
