package bizidgenerator.infrastructure;

import bizidgenerator.domain.BizIdVariable;
import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class BizIdGeneratorUtil {

    private BizIdGeneratorUtil() {}

    public static String buildBizId(BizIdVariable bizIdVariable, String seq) {
        String prefix = bizIdVariable.getPrefix();
        String formattedDate = BizIdGeneratorUtil.formatDate(bizIdVariable.getDayRule(), OffsetDateTime.now());
        String formattedSeq = BizIdGeneratorUtil.formatSeq(seq, bizIdVariable.getLength());

        return prefix + formattedDate + formattedSeq;
    }

    public static String formatDate(String datePattern, OffsetDateTime date) {
        if (StringUtils.isEmpty(datePattern)) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern(datePattern));
    }

    public static String formatSeq(String seq, Integer length) {
        return StringUtils.leftPad(seq, length, "0");
    }

}
