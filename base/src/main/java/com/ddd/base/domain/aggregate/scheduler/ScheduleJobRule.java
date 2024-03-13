package com.ddd.base.domain.aggregate.scheduler;

import com.ddd.base.infra.handler.ScheduleJobHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import com.example.common.utils.MapUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobRule {
    private static final String URL_REGEX = "\\$\\{([a-zA-Z-]*?)\\}";

    private String keyName;
    private String groupName;
    private String description;
    private Integer priority;
    private String cron;
    private CallTask callTask;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CallTask {
        private String serverName;
        private String url;
        private String method;
        private Map<String, String> requestBody = new HashMap<>();

        public void fillUrl(String serverUrl) {
            Matcher matcher = Pattern.compile(URL_REGEX).matcher(url);
            if (matcher.find()) {
                this.setUrl(matcher.replaceAll(serverUrl));
            }
        }
    }

    public JobDetail buildJobDetail() {
        return JobBuilder.newJob(ScheduleJobHandler.class)
                .withIdentity(keyName, groupName)
                .withDescription(description)
                .storeDurably()
                .requestRecovery()
                .usingJobData(new JobDataMap(MapUtil.convertToMap(callTask)))
                .build();
    }

    public Trigger buildTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity(keyName, groupName)
                .withDescription(description)
                .withPriority(priority)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .startNow()
                .build();
    }

}
