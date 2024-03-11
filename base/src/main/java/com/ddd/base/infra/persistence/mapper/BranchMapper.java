package com.ddd.base.infra.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.base.infra.persistence.po.BranchPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BranchMapper extends BaseMapper<BranchPO> {
}
