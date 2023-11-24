package com.medihub.medihub.repository;

import com.medihub.medihub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
