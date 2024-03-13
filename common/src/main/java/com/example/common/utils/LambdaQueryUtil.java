package com.example.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class LambdaQueryUtil {

    private LambdaQueryUtil() {}

    public static <T> LambdaQueryWrapper<T> generateEqualOrLikeQueryWrapper(String codeOrName,
                                                                            SFunction<T, String> getCode,
                                                                            SFunction<T, String> getName,
                                                                            LambdaQueryWrapper<T> wrapper) {
        LambdaQueryWrapper<T> tempWrapper = Objects.isNull(wrapper) ? Wrappers.lambdaQuery() : wrapper;

        String trimmedCodeOrName = StringUtils.trimToNull(codeOrName);
        if (StringUtils.isNotBlank(trimmedCodeOrName)) {
            tempWrapper.nested(w ->
                    w.eq(getCode, trimmedCodeOrName).or().like(getName, trimmedCodeOrName));
        }

        return tempWrapper;
    }

    public static <T> LambdaQueryWrapper<T> generateRangeQueryWrapper(Object start, Object end,
                                                                      SFunction<T, String> getQuery,
                                                                      LambdaQueryWrapper<T> wrapper) {
        LambdaQueryWrapper<T> tempWrapper = Objects.isNull(wrapper) ? Wrappers.lambdaQuery() : wrapper;

        return tempWrapper.nested(Objects.nonNull(start) || Objects.nonNull(end),
                    w -> w.ge(Objects.nonNull(start), getQuery, start)
                            .le(Objects.nonNull(end), getQuery, end));
    }

}
