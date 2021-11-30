package ir.imorate.jobify.service.security.Impl;

import ir.imorate.jobify.entity.security.Role;
import ir.imorate.jobify.entity.security.enums.RoleType;
import ir.imorate.jobify.repository.security.RoleRepository;
import ir.imorate.jobify.service.security.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRole(RoleType roleType) {
        return roleRepository.findRoleByType(roleType).orElseThrow(() ->
                new RuntimeException("Role: " + roleType + " not found"));
    }

}
