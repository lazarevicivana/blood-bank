package ftn.uns.ac.rs.bloodbank.applicationUser.model;

public enum UserRole {
    ROLE_NOT_REGISTER,
    ROLE_CENTER_ADMIN,
    ROLE_SYSTEM_ADMIN,
    ROLE_CUSTOMER;

    public static String toString(UserRole role) {
        return switch (role) {
            case ROLE_CENTER_ADMIN -> "Center admin";
            case ROLE_SYSTEM_ADMIN -> "System admin";
            case ROLE_CUSTOMER -> "Customer";
            default -> "Not register";
        };
    }

    public static UserRole getRoleFromString(String role) {
        switch (role) {
            case "Center admin":
                return UserRole.ROLE_CENTER_ADMIN;
            case "System admin":
                return UserRole.ROLE_SYSTEM_ADMIN;
            case "Customer":
                return UserRole.ROLE_CUSTOMER;
            default:
                return UserRole.ROLE_NOT_REGISTER;

        }
    }
}
