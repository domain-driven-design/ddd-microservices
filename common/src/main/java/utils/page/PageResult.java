package utils.page;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> {

    private List<T> records;
    private long total;
    private long size;
    private long current;
}
