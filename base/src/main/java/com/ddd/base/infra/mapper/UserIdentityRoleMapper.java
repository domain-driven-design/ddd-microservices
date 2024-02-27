package com.ddd.base.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.base.infra.po.UserIdentityRolePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserIdentityRoleMapper extends BaseMapper<UserIdentityRolePO> {
}
