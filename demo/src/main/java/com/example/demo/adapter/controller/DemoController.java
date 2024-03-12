package com.example.demo.adapter.controller;


import com.example.demo.application.calculation.args.DemoScheduleCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lock.DistributeLock;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @SneakyThrows
    @DistributeLock("#id")
    @GetMapping("/lock")
    public void testLock(@RequestParam("id") Long id) {
        System.out.println("starting execute");
        Thread.sleep(150000);
        System.out.println("execute ending");
    }

}
