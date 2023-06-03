
package com.publisher.managment.system.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> implements Serializable {

    private int totalPages;
    private long totalItems;
    private int currentPage;
    private int firstPage;
    private int lastPage;
    private int itemsPerPage;
    private int pageSize;
    private Sort sort;
    private List<T> items;

    public void setPageStats(Page pg) {
        currentPage = pg.getNumber() + 1;
        pageSize = pg.getSize();
        totalPages = pg.getTotalPages();
        totalItems = pg.getTotalElements();
        sort = pg.getSort();
        itemsPerPage = pg.getNumberOfElements();
        items = pg.getContent();
    }
}
