package com.ddd.base.adapter;


import com.ddd.base.application.dto.OperationLogCreateCommand;
import com.ddd.base.application.dto.OperationLogResponse;
import com.ddd.base.application.dto.query.OperationLogQueryDTO;
import com.ddd.base.application.service.OperationLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.page.PageResponse;

@RestController
@RequestMapping("/operation-log")
@AllArgsConstructor
public class OperationLogController {

    private final OperationLogService operationLogService;

    @GetMapping
    public ResponseEntity<PageResponse<OperationLogResponse>> query(OperationLogQueryDTO queryDTO) {
        return ResponseEntity.ok(operationLogService.query(queryDTO));
    }

    @PostMapping
    public ResponseEntity<OperationLogResponse> create(@RequestBody OperationLogCreateCommand createCommand) {
        return ResponseEntity.ok(operationLogService.create(createCommand));
    }

}
