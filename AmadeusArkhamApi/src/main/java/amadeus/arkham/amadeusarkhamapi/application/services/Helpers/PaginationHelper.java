package amadeus.arkham.amadeusarkhamapi.application.services.Helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class PaginationHelper<T> {
    private final int pageSize;

    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page<T> getPage(List<T> items, int pageNumber) {
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, items.size());

        return new PageImpl<>(items.subList(startIndex, endIndex), PageRequest.of(pageNumber, pageSize), items.size());
    }
}

