package com.techmind.project_enterprise.service;

import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface IProfileService {
    List<Profile> getAllProfile();
    Profile saveProfile (Profile profile);
    Profile getProfileById(Integer id);
    Profile updateProfile(Profile profile);
    void deleteProfileById(Integer id);
    boolean existSById(Integer id);
    Optional<Profile> getOne(Integer id);
}
