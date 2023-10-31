package com.nocountry.cashier.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.request
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableDto implements Pageable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer page;
    private Integer size;
    private Integer order;
    private String field;


    public Sort getSort() {
        if (field != null) {
            Sort.Direction direction = order == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
            return Sort.by(direction, field);
        }
        return Sort.unsorted();
    }

    @JsonIgnore
    @Override
    public Pageable next() {
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable first() {
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean hasPrevious() {
        return false;
    }

   @JsonIgnore
    @Override
    public int getPageNumber() {
        return page;
    }

    @JsonIgnore
    @Override
    public int getPageSize() {
        return size;
    }

    @JsonIgnore
    @Override
    public long getOffset() {
        return (long) page * (long) size;
    }


}



