package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByPhone(String phone);
    @Meta(comment = "Obtener todos los productos de una categoría específica")
    @Query("SELECT u from UserEntity u where lower(u.email) = lower(:mail)")
    Optional<UserEntity> findByEmailIgnoreCase(@Param(value = "mail") String mail);
    Optional<UserEntity> findByDni(String dni);


}
