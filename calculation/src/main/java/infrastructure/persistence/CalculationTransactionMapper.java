package infrastructure.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalculationTransactionMapper extends BaseMapper<CalculationTransactionPO> {}
