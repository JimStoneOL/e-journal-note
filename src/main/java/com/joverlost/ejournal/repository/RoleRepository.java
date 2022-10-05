package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.Role;
import com.joverlost.ejournal.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
