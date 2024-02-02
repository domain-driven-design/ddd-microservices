package datadictionary.application;

import datadictionary.DataDictionaryConfig;
import datadictionary.DataDictionaryResponse;
import datadictionary.DataDictionaryType;
import datadictionary.DataDictionaryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(DataDictionaryConfig.class)
public class DataDictionaryAppService {

    private final DataDictionaryConfig dataDictionaryConfig;

    @SuppressWarnings("unchecked")
    public List<DataDictionaryResponse> query() {
        try {
            Class<?> clazz = Class.forName(dataDictionaryConfig.getClassPath());
            if (!DataDictionaryType.class.isAssignableFrom(clazz)) {
                log.error("Class {} should implement DataDictionaryType!", dataDictionaryConfig.getClassPath());
                throw new IllegalArgumentException(); //todo replace by proper exception
            }

            return ((List<? extends DataDictionaryType>) Arrays.asList(clazz.getEnumConstants())).stream()
                    .map(DataDictionaryUtil::get)
                    .map(DataDictionaryResponse::of)
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException e) {
            log.error("Failed to find data dictionary class path {}", dataDictionaryConfig.getClassPath(), e);
            throw new IllegalArgumentException(); //todo replace by proper exception
        }
    }

}
