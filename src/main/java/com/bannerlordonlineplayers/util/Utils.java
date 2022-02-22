package com.bannerlordonlineplayers.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utils {

    public static Pageable getPagination(String order, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, order);
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}
