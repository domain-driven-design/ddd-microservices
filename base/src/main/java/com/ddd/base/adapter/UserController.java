package com.ddd.base.adapter;


import com.ddd.base.application.dto.UserCreateCommand;
import com.ddd.base.application.dto.UserResponse;
import com.ddd.base.application.dto.query.UserQueryDTO;
import com.ddd.base.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.page.PageResponse;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    //查询	实现用户信息检索和筛选功能
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> query(UserQueryDTO userQuery) {
        return ResponseEntity.ok(userService.query(userQuery));
    }

    //注册	开发用户注册流程
    @PostMapping
    public ResponseEntity <UserResponse> register(@RequestBody UserCreateCommand createDTO) {
        return ResponseEntity.ok(userService.register(createDTO));
    }

    //停用/恢复	设定用户账户的停用和恢复机制
    @PutMapping("/{id}/disable")
    public ResponseEntity<UserResponse> disable(@PathVariable String id) {
        return ResponseEntity.ok(userService.disable(id));
    }

    @PutMapping("/{id}/enable")
    public ResponseEntity<UserResponse> enable(@PathVariable String id) {
        return ResponseEntity.ok(userService.enable(id));
    }

    //身份切换	提供用户角色及权限的动态切换功能
    @PutMapping("/{id}/switch")
    public ResponseEntity<UserResponse> switchIdentity(@PathVariable String id, @RequestParam String identityId) {
        return ResponseEntity.ok(userService.switchIdentity(id, identityId));
    }
}
