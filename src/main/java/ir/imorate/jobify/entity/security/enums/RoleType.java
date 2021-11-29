package ir.imorate.jobify.entity.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    ROLE_USER("User"),
    ROLE_ADMIN("Admin"),
    ORG_ADMIN("Organization Admin");

    private final String title;

}
