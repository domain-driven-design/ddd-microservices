package com.ddd.base.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.base.infra.po.UserIdentityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserIdentityMapper extends BaseMapper<UserIdentityPO> {
}
