package com.smartinventory.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PageableUtil {

    private PageableUtil() {
    }

    public static Pageable createPageable(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction) {

        return PageRequest.of(
                page,
                size,
                Sort.by(direction, sortBy)
        );
    }
}
