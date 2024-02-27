package com.ddd.base.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.base.infra.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
