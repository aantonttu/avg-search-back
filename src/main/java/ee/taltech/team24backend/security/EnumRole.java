package ee.taltech.team24backend.security;

public enum EnumRole {
    USER, ADMIN;

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public String toStringRole() {
        return "ROLE_" + this.name();
    }
}
