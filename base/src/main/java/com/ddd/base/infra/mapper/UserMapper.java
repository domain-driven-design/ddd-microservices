package com.ddd.base.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.base.application.dto.query.UserQueryDTO;
import com.ddd.base.infra.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    Page<UserPO> selectPage(Page<UserPO> page, @Param("queryDTO") UserQueryDTO queryDTO);

}
