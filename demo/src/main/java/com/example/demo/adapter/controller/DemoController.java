package com.example.demo.adapter.controller;


import com.example.demo.application.calculation.args.DemoScheduleCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("演示接口")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {

    @PutMapping("/schedule")
    @ApiOperation("定时任务演示")
    public void schedule(@RequestBody DemoScheduleCommand command) {
        log.info("schedule task was called! input is {}", command.getMockInput());
    }

}
