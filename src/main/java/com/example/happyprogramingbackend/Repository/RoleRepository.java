package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.Entity.Role;
import com.example.happyprogramingbackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
