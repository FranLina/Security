package com.llv.exament4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llv.exament4.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}

