package com.example.kepbongeszo_be.Repository;

import com.example.kepbongeszo_be.Model.Picture;
import com.example.kepbongeszo_be.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query(value = "SELECT p FROM Picture p WHERE ?1 member of p.visibilityList")
    Set<Picture> findAllByRole(Role r);
}
