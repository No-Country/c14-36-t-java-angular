package com.nocountry.cashier.controller.dto.request;

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

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return (long) page * (long) size;
    }


}

//    @Override
//    public int getPageNumber() {
//        return page;
//    }
//
//    @Override
//    public int getPageSize() {
//        return size;
//    }
//
//    @Override
//    public long getOffset() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
////    @Override
////    public Sort getSort() {
////        // TODO Auto-generated method stub
////        return null;
////    }
//
//    @Override
//    public Pageable next() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Pageable previousOrFirst() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Pageable first() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Pageable withPage(int pageNumber) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public boolean hasPrevious() {
//        // TODO Auto-generated method stub
//        return false;
//    }


