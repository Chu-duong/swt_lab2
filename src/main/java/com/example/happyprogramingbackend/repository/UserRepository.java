package com.example.happyprogramingbackend.repository;

import com.example.happyprogramingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT m FROM User m WHERE (m.firstName LIKE %?1% or m.middleName LIKE %?1% or m.lastName LIKE %?1%) and m.role.id = ?2")
    List<User> searchAllBy(String keyword, Long roleId);

    @Query("SELECT m FROM User m WHERE (m.firstName LIKE %?1% or m.middleName LIKE %?1% or m.lastName LIKE %?1%) and m.role.id = ?2 and m.isActive = ?3")
    List<User> searchAllWithStatus(String keyword, Long roleId, Boolean isActive);

=======
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  @Query(
      "SELECT m FROM User m WHERE (m.firstName LIKE %?1% or m.middleName LIKE %?1% or m.lastName LIKE %?1%) and m.role.id = ?2")
  List<User> searchAllBy(String keyword, Long roleId);

  @Query(
      "SELECT m FROM User m WHERE (m.firstName LIKE %?1% or m.middleName LIKE %?1% or m.lastName LIKE %?1%) and m.role.id = ?2 and m.isActive = ?3")
  List<User> searchAllWithStatus(String keyword, Long roleId, Boolean isActive);
>>>>>>> c44d9fd (fix package name)
}
