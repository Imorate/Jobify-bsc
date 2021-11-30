package ir.imorate.jobify.service.security;

import ir.imorate.jobify.entity.security.Role;
import ir.imorate.jobify.entity.security.enums.RoleType;

public interface RoleService {

    void create(Role role);

    Role findRole(RoleType roleType);

}
