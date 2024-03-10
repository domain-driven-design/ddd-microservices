package com.ddd.base.infra.handler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class ScheduleJobHandler implements Job {

    private static final String REST_TEMPLATE_BEAN_NAME = "scheduleRestTemplate";
    private static final String SERVER_URL_KEY = "url";
    private static final String SERVER_NAME_KEY = "serverName";
    private static final String REQUEST_METHOD_KEY = "method";
    private static final String REQUEST_BODY_KEY = "requestBody";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String serverName = jobDataMap.getString(SERVER_NAME_KEY);
        String jobName = context.getJobDetail().getKey().getName();

        try {
            RestTemplate restTemplate = getRestTemplate(context);
            restTemplate.exchange(buildRequest(jobDataMap), new ParameterizedTypeReference<Object>() {});
            log.info("[ScheduleJobManager] service {} execute {} succeed", serverName, jobName);
        } catch (Exception e) {
            log.error("[ScheduleJobManager] service {} execute {} failed. Error:", serverName, jobName, e);
            throw new JobExecutionException(e);
        }
    }

    private RequestEntity<Object> buildRequest(JobDataMap jobDataMap) throws URISyntaxException {
        HttpMethod requestMethod = HttpMethod.resolve(jobDataMap.getString(REQUEST_METHOD_KEY));
        Object requestBody = jobDataMap.get(REQUEST_BODY_KEY);
        URI uri = new URI(jobDataMap.getString(SERVER_URL_KEY));
        Assert.notNull(requestMethod, "requestMethod configuration cannot be null");

        return new RequestEntity<>(requestBody, requestMethod, uri);
    }

    private RestTemplate getRestTemplate(JobExecutionContext context) throws SchedulerException {
        ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        return applicationContext.getBean(REST_TEMPLATE_BEAN_NAME, RestTemplate.class);
    }

}
