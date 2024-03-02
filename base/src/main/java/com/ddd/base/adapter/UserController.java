package com.ddd.base.adapter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.service.UserService;
import com.ddd.base.application.service.dto.UserCreateDTO;
import com.ddd.base.application.service.dto.UserResponse;
import com.ddd.base.application.service.dto.query.UserQueryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    //查询	实现用户信息检索和筛选功能
    @GetMapping
    public ResponseEntity<Page<UserResponse>> query(UserQueryDTO userQuery) {
        return ResponseEntity.ok(userService.query(userQuery));
    }

    //注册	开发用户注册流程
    @PostMapping
    public ResponseEntity<UserResponse> register(UserCreateDTO createDTO) {
        return ResponseEntity.ok(userService.register(createDTO));
    }

    //停用/恢复	设定用户账户的停用和恢复机制
    @PutMapping("/disable/{id}")
    public ResponseEntity<UserResponse> disable(String id) {
        return ResponseEntity.ok(userService.disable(id));
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<UserResponse> enable(String id) {
        return ResponseEntity.ok(userService.enable(id));
    }

    //身份切换	提供用户角色及权限的动态切换功能
    @PutMapping("/switch/{id}")
    public ResponseEntity<UserResponse> switchIdentity(String id) {
        return ResponseEntity.ok(userService.switchIdentity(id));
    }
}
