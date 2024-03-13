package com.ddd.base.domain.aggregate;

import com.example.common.businessrule.IBusinessRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessRule implements IBusinessRule {

    SCHEDULE_JOB_RULE("schedule_job_rule", "定时任务规则");

    private String ruleName;
    private String description;

}
