package bizidgenerator.infrastructure;

import bizidgenerator.domain.BizType;

public interface BizIdGenerator {

    static final Long INITIAL_SEQ = 1L;

    String getBizId(BizType type);

    default void clearCache(BizType type) {}

}
