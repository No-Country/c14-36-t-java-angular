package com.nocountry.cashier.persistance.repository;

import com.nocountry.cashier.persistance.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Meta(comment = "Obtener todos los productos de una categoría específica")
    @Query("SELECT u from UserEntity u where lower(u.email) = lower(:mail)")
    Optional<UserEntity> findByEmailIgnoreCase(@Param(value = "mail") String mail);
    Optional<UserEntity> findByDni(String dni);
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u set u.verify=true where lower(u.email)= ?1")
    void setVerifiedUser(String email);

    @Query("UPDATE UserEntity u set u.enabled=true where lower(u.email)= ?1")
    int enabledUser(String email);


//    @Query(value = "SELECT u FROM UserEntity u WHERE u.name LIKE '%'+:ramdom+'%' OR u.lastName LIKE '%'+:ramdom+'%'")
////            countQuery = "SELECT COUNT(u) FROM UserEntity u WHERE u.name LIKE '%'+:ramdom+'%'OR u.lastName LIKE '%'+:ramdom+'%'")
//    Page<UserEntity> findByShortString(@Param("ramdom") String ramdom, Pageable pageable);
    @Meta(comment = "Obtener todos los usuarios segun caracteres ingresados ej:Car....")
    @Query("SELECT u FROM UserEntity u WHERE lower(u.name) LIKE %:ramdom% OR lower(u.lastName) LIKE %:ramdom%")
    Page<UserEntity> findByShortString(@Param("ramdom") String ramdom, Pageable pageable);



    /*
    *  @Query(value = "SELECT t FROM TransactionEntity t WHERE t.accountEntity.idAccount= :id_account",
            countQuery = "SELECT COUNT(t) FROM TransactionEntity t WHERE t.accountEntity.idAccount = :id_account")
    Page<TransactionEntity> findAllByIdAccount( @Param("id_account") String id_account,Pageable pageable);*/

    //eL PUEDA BUSCAR EN LA BASE POR 3LETRAS QUE PUEDEN COINCIDIR CON NAME O LASTNAME
}
