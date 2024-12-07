package com.chauffeurservice.controller;

import com.chauffeurservice.model.AppUser;
import com.chauffeurservice.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/test")
    public ResponseEntity<String> testRequest() {
        return new ResponseEntity<>("AppUserController Test Success", HttpStatus.ACCEPTED);
    }

    // Create: Yeni kullanıcı kaydetme
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser appUser) {
        if (appUserService.getAppUserByUsername(appUser.getUsername()).isPresent()) {
            return new ResponseEntity<>("Kullanıcı Adı zaten mevcut", HttpStatus.BAD_REQUEST);
        }
        if (appUserService.getAppUserByEmail(appUser.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email zaten mevcut", HttpStatus.BAD_REQUEST);
        }
        appUserService.saveAppUser(appUser);
        return new ResponseEntity<>("Kullanıcı Oluşturuldu", HttpStatus.CREATED);
    }


    // Read: Tüm kullanıcıları listeleme
    @GetMapping()
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Read: Kullanıcıyı ID ile getirme
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        Optional<AppUser> appUser = appUserService.getAppUserById(id);
        return appUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update: Kullanıcı bilgilerini güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        Optional<AppUser> existingUser = appUserService.getAppUserById(id);
        if (existingUser.isPresent()) {
            appUser.setUserId(id);
            appUserService.saveAppUser(appUser);
            return new ResponseEntity<>("Kullanıcı bilgileri güncellendi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND);
        }
    }

    // Delete: Kullanıcıyı silme
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<AppUser> existingUser = appUserService.getAppUserById(id);
        if (existingUser.isPresent()) {
            appUserService.deleteAppUser(id);
            return new ResponseEntity<>("Kullanıcı silindi. ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND);
        }
    }

}
