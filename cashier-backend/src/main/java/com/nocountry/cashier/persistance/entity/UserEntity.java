package com.nocountry.cashier.persistance.entity;

import com.nocountry.cashier.controller.dto.request.UpdateRequestDTO;
import com.nocountry.cashier.persistance.entity.listener.audit.Auditable;
import com.nocountry.cashier.util.Utility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@SQLDelete(sql = "UPDATE customer SET enabled=FALSE where id=?")
@Table(name = "customer")
@Where(clause = "enabled=TRUE")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends Auditable<LocalDateTime> {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", nullable = false, length = 85)
    private String name;

    @Column(name = "last_name", nullable = false, length = 95)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String dni;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 110) //nullabe
    private String email;

    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    //private LocalDateTime openAccountDate;

    private Boolean enabled;

    @Column(name = "verify")
    private Boolean verify;

    private String password;

    private String qr;
    private String codeOtp;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "url_profile")
    private ImageEntity image;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_token")
    private TokenEntity token;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userEntity")

    private AccountEntity accountEntity;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_card")
    private CreditCardEntity creditCardEntity;

    @ManyToMany
    @JoinTable(
            name = "contacts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<UserEntity> contacts;

    @PrePersist
    public void onCreate() {
        this.setVerify(Boolean.FALSE);
        this.setEnabled(Boolean.TRUE);
    }

    public void addContact(UserEntity contact) {
        contacts.add(contact);
    }

    public void deleteContact(UserEntity contact) {
        contacts.remove(contact);
    }


    public UserEntity modifyUser(UpdateRequestDTO requestDTO) {
        if (StringUtils.hasText(requestDTO.getName())) this.setName(requestDTO.getName().strip());
        if (StringUtils.hasText(requestDTO.getLastName())) this.setLastName(requestDTO.getLastName().strip());
        if (StringUtils.hasText(requestDTO.getPhone())) this.setPhone(requestDTO.getPhone().strip());
        if (StringUtils.hasText(requestDTO.getEmail())) this.setEmail(requestDTO.getEmail().strip());
        if (StringUtils.hasText(requestDTO.getDni())) this.setDni(requestDTO.getDni().strip());
        if (StringUtils.hasText(requestDTO.getAddress())) this.setAddress(requestDTO.getAddress().strip());
        if (StringUtils.hasText(requestDTO.getBirthDate()))
            this.setBirthDate(Utility.stringToLocalDate(requestDTO.getBirthDate()));
        return this;
    }

}

