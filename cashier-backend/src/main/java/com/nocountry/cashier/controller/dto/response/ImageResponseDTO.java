package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.response
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@JsonPropertyOrder({"url", "filename", "type"})
public class ImageResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("filename")
    private String fileName;
    @JsonProperty("type")
    private String fileType;
    @JsonProperty("url")
    private String urlImage;
}