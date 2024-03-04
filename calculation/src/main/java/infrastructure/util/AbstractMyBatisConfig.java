package infrastructure.util;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.context.annotation.Bean;

public abstract class AbstractMyBatisConfig {

    @Bean
    public ConfigurationCustomizer myBatisCustomizer() {
        return this::customize;
    }

    protected abstract void customize(Configuration configuration);

    protected <E> void registerEnumTypeHandler(Configuration configuration,
                                               Class<E> enumClass,
                                               TypeHandler<? extends E> typeHandler) {
        configuration.getTypeHandlerRegistry().register(enumClass, typeHandler);
    }

}
