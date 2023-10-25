package com.nocountry.cashier.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import static java.util.Objects.nonNull;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.entity
 * @license Lrpa, zephyr cygnus
 * @since 9/10/2023
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "image")
@AllArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "file_name", updatable = true)
    private String fileName;

    @Column(name = "file_type", updatable = true)
    private String fileType;

    @Column(name = "url_image", updatable = true)
    private String urlImage;

    public boolean isValidImage() {
        return nonNull(urlImage) && nonNull(fileName);
    }

}

