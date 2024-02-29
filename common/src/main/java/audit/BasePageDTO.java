package audit;

import lombok.Data;

@Data
public class BasePageDTO {
    private Integer currentPage = 1;
    private Integer pageSize = 10;
}
