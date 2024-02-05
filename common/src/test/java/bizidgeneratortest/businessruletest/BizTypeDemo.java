package bizidgeneratortest.businessruletest;

import bizidgenerator.domain.BizType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BizTypeDemo implements BizType {

    DEMO1("desc1"),
    DEMO2("desc2");

    private String description;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public Map<String, String> getExtraFields() {
        return BizType.super.getExtraFields();
    }
}
