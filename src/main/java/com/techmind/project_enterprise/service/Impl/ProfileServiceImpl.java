package com.techmind.project_enterprise.service.Impl;

import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.repository.IProfileRepository;
import com.techmind.project_enterprise.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements IProfileService {
    @Autowired
    public IProfileRepository repoprofile;
    @Override
    public List<Profile> getAllProfile() {

        return repoprofile.findAll();
    }

    @Override
    public Profile saveProfile(Profile profile) {

        return repoprofile.save(profile);
    }

    @Override
    public Profile getProfileById(Integer id) {

        return repoprofile.findById(id).get();
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return repoprofile.save(profile);
    }

    @Override
    public void deleteProfileById(Integer id) {
        repoprofile.deleteById(id);

    }

    @Override
    public boolean existSById(Integer id) {
        return repoprofile.existsById(id);
    }

    @Override
    public Optional<Profile> getOne(Integer id) {
        return repoprofile.findById(id);
    }
}
