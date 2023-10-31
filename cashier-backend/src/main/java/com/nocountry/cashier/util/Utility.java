package com.nocountry.cashier.util;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
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
    /*
    * public Pageable setPageable(PageableDto pageableDto) {
    Sort sort = Sort.by(pageableDto.getSortDirection(), pageableDto.getSortBy()); // Configura la ordenación
    return PageRequest.of(pageableDto.getPage(), pageableDto.getSize(), sort); // Configura el objeto Pageable
}
*/

    public String generatorOTP(int length){
        SecureRandom random= new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0){
                int randomNumber = random.nextInt(10);
                sb.append(randomNumber);
            }else {
                char randomChar = (char) (random.nextInt(26) + 'a');
                 if (random.nextBoolean()) randomChar=Character.toUpperCase(randomChar);
                 sb.append(randomChar);
            }
        }
        return sb.toString();

    }
    public String urlServer(HttpServletRequest request, String apiEndpoint){
        String protocol = request.getScheme(); // HTTP o HTTPS
        String serverName = request.getServerName(); // Nombre del servidor
        int serverPort = request.getServerPort(); // Puerto del servidor
        String contextPath = request.getContextPath(); // Contexto de la aplicación web
        // Construye la URL completa
        return protocol + "://" + serverName + ":" + serverPort + contextPath + apiEndpoint;
    }

    /**
     * Validación de correo solo gmail
     *
     * @param email String
     * @return boolean
     */
    public static boolean validateEmail(String email) {
        email = StringUtils.trimAllWhitespace(email);
        // * Expresión regular para validar un correo de gmail original ->^[a-z0-9ñÑ]+(?!.*(?:\+{2,}|\-{2,}|\.{2,}))(?:[\.+\-_]{0,1}[a-z0-9Ññ])*@gmail\.com$
        Pattern emailRegex = Pattern.compile("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = emailRegex.matcher(email);

        return matcher.find();
    }

    public static LocalDate stringToLocalDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(birthDate.strip(), formatter);
    }


}
