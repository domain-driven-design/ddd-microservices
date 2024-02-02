package datadictionary;

public interface DataDictionaryType {

    Class<? extends Description> getClazz();
    String getDescription();

}
