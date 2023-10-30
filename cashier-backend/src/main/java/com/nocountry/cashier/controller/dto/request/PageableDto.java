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
    public boolean isPaged() {
        return Pageable.super.isPaged();
    }
    @JsonIgnore
    @Override
    public boolean isUnpaged() {
        return Pageable.super.isUnpaged();
    }

    @JsonIgnore
    @Override
    public long getOffset() {
        // TODO Auto-generated method stub
        return 0;
    }

    @JsonIgnore
    @Override
    public Sort getSort() {
        // TODO Auto-generated method stub
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable next() {
        // TODO Auto-generated method stub
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable previousOrFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }

    @JsonIgnore
    @Override
    public Pageable withPage(int pageNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @JsonIgnore
    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }

}
