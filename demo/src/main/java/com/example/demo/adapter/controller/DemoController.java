package com.example.demo.adapter.controller;


import com.example.calculation.application.service.CalculationAppService;
import com.example.common.lock.DistributeLock;
import com.example.demo.application.calculation.args.DemoScheduleCommand;
import com.example.demo.domain.aggregate.calculation.DemoCalculationMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final CalculationAppService calculationAppService;

    @PutMapping("/schedule")
    @ApiOperation("定时任务演示")
    public void schedule(@RequestBody DemoScheduleCommand command) {
        log.info("schedule task was called! input is {}", command.getMockInput());
    }

    @SneakyThrows
    @DistributeLock("#id")
    @GetMapping("/com/example/common/lock")
    public void testLock(@RequestParam("id") Long id) {
        System.out.println("starting execute");
        Thread.sleep(150000);
        System.out.println("execute ending");
    }

    /**
     * A demo api used to show the use of generateFlow method, which accepts a calculation mode
     * and a file path to generate a calculation flow
     * @param mode The calculation mode which determines the type of calculation flow to be created
     * @param path The filesystem path where the generated calculation flow will be saved
     * @return
     */
    @PostMapping("/flow")
    @ApiOperation("create calculation flow")
    public ResponseEntity<String> generateFlow(@RequestParam DemoCalculationMode mode, @RequestParam String path) {
        calculationAppService.generateFlow(mode, path);
        return ResponseEntity.ok("Calculation flow generated successfully");
    }

}
