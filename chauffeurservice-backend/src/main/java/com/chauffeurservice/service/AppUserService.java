package com.chauffeurservice.service;

import com.chauffeurservice.model.AppUser;
import com.chauffeurservice.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;


    // Kullanıcıyı kaydetme (create)
    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    // Tüm kullanıcıları listeleme
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    // ID ile kullanıcıyı bulma
    public Optional<AppUser> getAppUserById(Long id) {
        return appUserRepository.findById(id);
    }

    // Kullanıcı adınıyla kullanıcıyı bulma
    public Optional<AppUser> getAppUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    // E-posta ile kullanıcıyı bulma
    public Optional<AppUser> getAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    // Kullanıcıyı silme
    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }

}

