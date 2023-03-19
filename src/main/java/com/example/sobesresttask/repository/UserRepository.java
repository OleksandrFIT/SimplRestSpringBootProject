package com.example.sobesresttask.repository;

import com.example.sobesresttask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
