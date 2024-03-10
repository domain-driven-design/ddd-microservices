package com.ddd.base.adapter.scheduler;

import com.ddd.base.application.service.ScheduleJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleJobRunner implements CommandLineRunner {

    private final ScheduleJobService scheduleJobService;

    @Override
    public void run(String... args) {
        scheduleJobService.startJob();
    }

}
