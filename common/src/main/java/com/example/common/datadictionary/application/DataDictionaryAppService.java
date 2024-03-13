package com.example.common.datadictionary.application;

import com.example.common.datadictionary.DataDictionaryConfig;
import com.example.common.datadictionary.DataDictionaryResponse;
import com.example.common.datadictionary.DataDictionaryType;
import com.example.common.datadictionary.DataDictionaryUtil;
import com.example.common.error.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.common.error.CommonError.FILE_NOT_EXIST;
import static com.example.common.error.CommonError.ILLEGAL_DATA_DICTIONARY;

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
                throw new BusinessException(ILLEGAL_DATA_DICTIONARY, clazz.getName());
            }

            return ((List<? extends DataDictionaryType>) Arrays.asList(clazz.getEnumConstants())).stream()
                    .map(DataDictionaryUtil::get)
                    .map(DataDictionaryResponse::of)
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException e) {
            log.error("Failed to find data dictionary class path {}", dataDictionaryConfig.getClassPath(), e);
            throw new BusinessException(FILE_NOT_EXIST, dataDictionaryConfig.getClassPath());
        }
    }

}
