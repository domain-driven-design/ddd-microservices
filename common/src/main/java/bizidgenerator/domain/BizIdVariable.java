package bizidgenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizIdVariable {

    /**
     * 业务类型
     */
    private BizType bizType;
    /**
     * ID前缀
     */
    private String prefix;
    /**
     * ID长度
     */
    private Integer length;
    /**
     * ID范围增长步长
     */
    private Integer step;
    /**
     * 日期规则
     */
    private String dayRule;
    /**
     * 过期时间
     */
    private Integer expireDays;

    public boolean hasExpireDays() {
        return Objects.nonNull(expireDays) && expireDays > 0;
    }

}
