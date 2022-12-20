package ftn.uns.ac.rs.bloodbank.applicationUser.controller;


import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserUpdate;
import ftn.uns.ac.rs.bloodbank.applicationUser.service.ApplicationUserService;

import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserDtoResponse;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/applicationUser")
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;
    private final MapperService mapperService;

    public ApplicationUserController(ApplicationUserService applicationUserService, MapperService mapperService){
        this.applicationUserService = applicationUserService;
        this.mapperService = mapperService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUserUpdate> getApplicationUser(@NonNull @PathVariable("id") UUID id){
        var user = mapperService.AppUserToAppUserUpdate((applicationUserService.getApplicationUser(id)));
        return ResponseEntity.ok(user);
    }

    @PutMapping()
    public ResponseEntity<String> updateApplicationUser(@RequestBody ApplicationUserUpdate userDto){
        var user = mapperService.AppUserUpdateToAppUser(userDto);
        applicationUserService.updateApplicationUser(user);
        return ResponseEntity.noContent().build();
    }
    @GetMapping()
    public ResponseEntity<List<ApplicationUserDtoResponse>> getAllUsers(){
        var users = applicationUserService
                .getAllAplicationUsers()
                .stream()
                .map(mapperService::AppUserToAppUserDto)
                .toList();
        return ResponseEntity.ok(users);
    }
}
