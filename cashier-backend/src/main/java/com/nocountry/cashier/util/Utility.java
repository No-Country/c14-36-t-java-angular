package com.nocountry.cashier.util;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.util
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@Component
public final class Utility {

    // Region metodo que permite ordenar y paginar
    public Pageable setPageable(PageableDto pageableDTO) {
        Integer sortOrder = pageableDTO.getOrder();
        String sortField = pageableDTO.getField();
        int pageNumber = pageableDTO.getPageNumber();
        int perPage = pageableDTO.getPageSize();

        Pageable pageable;
        if (Objects.nonNull(sortOrder) && !sortField.isBlank()) {
            Sort.Direction direction = sortOrder.equals(1) ? Sort.Direction.ASC : Sort.Direction.DESC;
            pageable = PageRequest.of(pageNumber, perPage, Sort.by(direction, sortField));
        } else {
            pageable = PageRequest.of(pageNumber, perPage, Sort.by("id").descending());
        }
        return pageable;
    }

    /**
     * Validación de correo solo gmail
     *
     * @param email String
     * @return boolean
     */
    public static boolean validateEmail(String email) {
        email = StringUtils.trimAllWhitespace(email);
        Pattern emailRegex = Pattern.compile("^[a-z0-9ñÑ]+(?!.*(?:\\+{2,}|\\-{2,}|\\.{2,}))(?:[\\.+\\-_]{0,1}[a-z0-9Ññ])*@gmail\\.com$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = emailRegex.matcher(email);
        return matcher.find();
    }

    public static LocalDate stringToLocalDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(birthDate.strip(), formatter);
    }


}
