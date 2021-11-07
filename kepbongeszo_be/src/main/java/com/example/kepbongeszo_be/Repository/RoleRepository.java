package com.example.kepbongeszo_be.Repository;

import com.example.kepbongeszo_be.Model.ERole;
import com.example.kepbongeszo_be.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
