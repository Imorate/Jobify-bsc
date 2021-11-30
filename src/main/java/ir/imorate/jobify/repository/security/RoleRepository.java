package ir.imorate.jobify.repository.security;

import ir.imorate.jobify.entity.security.Role;
import ir.imorate.jobify.entity.security.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByType(RoleType roleType);

}