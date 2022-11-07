package ftn.uns.ac.rs.bloodbank.applicationUser;

public enum UserRole {

    ROLE_CENTER_ADMIN,
    ROLE_SYSTEM_ADMIN,
    ROLE_NOT_REGISTER,
    ROLE_CUSTOMER;

    public static String toString(UserRole role) {
        switch (role) {
            case ROLE_CENTER_ADMIN:
                return "Center admin";
            case ROLE_SYSTEM_ADMIN:
                return "System admin";
            case ROLE_CUSTOMER:
                return "Customer";
            default:
                return "Not register";
        }
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
