package datadictionary;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataDictionaryUtil {

    private DataDictionaryUtil() {}

    public static DataDictionary get(DataDictionaryType type) {
        List<? extends Description> descriptions = Arrays.asList(type.getClazz().getEnumConstants());

        return DataDictionary.builder()
                .name(type.getClazz().getSimpleName())
                .description(type.getDescription())
                .attributes(descriptions.stream()
                        .map(it -> DataDictionary.DictionaryAttribute.builder()
                                .code(it.getCode())
                                .description(it.getDescription())
                                .extraFields(it.getExtraFields())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
