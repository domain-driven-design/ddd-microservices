package com.ddd.base.adapter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.service.UserService;
import com.ddd.base.application.service.dto.UserDTO;
import com.ddd.base.application.service.dto.query.UserQueryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    //    查询	实现用户信息检索和筛选功能
    @GetMapping
    public ResponseEntity<Page<UserDTO>> query(UserQueryDTO userQuery) {
        return userService.query(userQuery);
    }
//    注册	开发用户注册流程
//    停用/恢复	设定用户账户的停用和恢复机制
//    身份切换	提供用户角色及权限的动态切换功能
}
