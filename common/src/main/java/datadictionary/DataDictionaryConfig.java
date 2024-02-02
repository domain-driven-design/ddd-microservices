package datadictionary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "data-dictionary")
public class DataDictionaryConfig {

    private String classPath;

}
