package com.ddd.base.adapter;


import com.ddd.base.application.dto.BranchCreateCommand;
import com.ddd.base.application.dto.BranchResponse;
import com.ddd.base.application.dto.query.BranchQueryDTO;
import com.ddd.base.application.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.page.PageResponse;

@RestController
@RequestMapping("/branches")
@AllArgsConstructor
public class BranchController {

    private final BranchService branchService;

    //查询	创建机构数据的查询接口
    @GetMapping
    public ResponseEntity<PageResponse<BranchResponse>> query(BranchQueryDTO branchQueryDTO) {
        return ResponseEntity.ok(branchService.query(branchQueryDTO));
    }

    // 新增、删除	实现机构的添加和移除流程
    @PostMapping
    public ResponseEntity <BranchResponse> create(@RequestBody BranchCreateCommand createDTO) {
        return ResponseEntity.ok(branchService.create(createDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity <BranchResponse> delete(@PathVariable String id) {
        return ResponseEntity.ok(branchService.delete(id));
    }
}
