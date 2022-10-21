package ftn.bloodbankbe.aplicationUser.model;

import ftn.bloodbankbe.customer.Profession;

import java.util.UUID;

public class ApplicationUser {
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    private Address address;
    private GenderType gender;
    private Profession profession;

}
