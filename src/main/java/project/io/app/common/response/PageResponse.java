package project.io.app.common.response;

import lombok.*;
import org.springframework.data.domain.*;

import java.util.*;

@Getter
public class PageResponse<T> {

    private List<T> content;
    private int totalPage;
    private long totalCount;
    private int pageNumber;
    private int pageSize;
    private Sort sort;

    private PageResponse() {
    }

    public PageResponse(
        List<T> content,
        int totalPage,
        long totalCount,
        Pageable pageable
    ) {
        this.content = content;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageNumber = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
        this.sort = pageable.getSort();
    }
}
