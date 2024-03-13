package com.ddd.base.application.service;

import com.example.common.businessrule.BusinessRuleProvider;
import com.ddd.base.domain.aggregate.BusinessRule;
import com.ddd.base.domain.aggregate.scheduler.ScheduleJobRule;
import com.ddd.base.infra.config.ServerUrlConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleJobService {

    private final Scheduler scheduler;
    private final BusinessRuleProvider businessRuleProvider;
    private final ApplicationContext applicationContext;
    private final ServerUrlConfig serverUrlConfig;

    public void startJob() {
        try {
            List<ScheduleJobRule> jobs = prepareJobs();
            jobs.forEach(this::register);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.warn("[ScheduleJobService] failed to start scheduler, error: ", e);
        }
    }

    private List<ScheduleJobRule> prepareJobs() {
        // load job rules
        List<ScheduleJobRule> rules = businessRuleProvider.getBusinessRule(
                BusinessRule.SCHEDULE_JOB_RULE.getRuleName(), new TypeReference<List<ScheduleJobRule>>() {});
        // fill url based on service name
        rules.forEach(r -> r.getCallTask().fillUrl(serverUrlConfig.getUrl(r.getGroupName())));
        return rules;
    }

    private void register(ScheduleJobRule scheduleJobRule) {
        String keyName = scheduleJobRule.getKeyName();
        String groupName = scheduleJobRule.getGroupName();
        try {
            // remove existing jobs
            scheduler.deleteJob(new JobKey(keyName, groupName));

            // add new jobs
            Trigger jobTrigger = scheduleJobRule.buildTrigger();
            JobDetail jobDetail = scheduleJobRule.buildJobDetail();
            autoWireJobBean(jobDetail);
            scheduler.scheduleJob(jobDetail, jobTrigger);

        } catch (SchedulerException e) {
            log.warn("[Scheduler] failed to remove/add job with key {}", keyName, e);
        }
    }

    private void autoWireJobBean(JobDetail jobDetail) {
        try {
            AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
            beanFactory.autowireBean(jobDetail.getJobClass().getDeclaredConstructor().newInstance());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException| NoSuchMethodException e) {
            log.warn("[Scheduler] failed to auto wire beans", e);
        }
    }

}
