package businessruletest;

import businessrule.IBusinessRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DemoBusinessRule implements IBusinessRule {

    DEMO1("demo1", "desc1"),
    DEMO2("demo2", "desc2");

    private String ruleName;
    private String description;

}
