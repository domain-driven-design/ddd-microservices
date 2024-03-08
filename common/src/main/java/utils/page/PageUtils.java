package utils.page;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static <T> PageResponse<T> getPage(@NotNull List<T> originList, PageQuery pageQuery) {
        if (originList.isEmpty()) {
            return new PageResponse<>();
        }

        List<T> resultList = new ArrayList<>();
        if (pageQuery.getPageNumber() > 0 && pageQuery.getPageSize() > 0) {
            Long pageStart = (pageQuery.getPageNumber() - 1) * pageQuery.getPageSize();
            Long pageStop = pageStart + pageQuery.getPageSize();
            while (pageStart < pageStop) {
                if (pageStart >= originList.size()) {
                    break;
                }
                resultList.add(originList.get(pageStart.intValue()));
                pageStart++;
            }
        }

        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setTotal(originList.size());
        pageResponse.setRecords(resultList);
        pageResponse.setSize(resultList.size());
        pageResponse.setCurrent(pageQuery.getPageNumber());
        return pageResponse;

    }
}
