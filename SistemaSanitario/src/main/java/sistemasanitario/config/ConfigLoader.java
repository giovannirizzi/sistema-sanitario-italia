package sistemasanitario.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigLoader {
    
    public static final String ENCODING = "UTF-8";
    public static final char LIST_DELIMITER = ',';
    public static final boolean THROW_EXCEPTION_ON_MISSING = true;
    public static final boolean INCLUDES_ALLOWED = true;

    public static PropertiesConfiguration get(String fileName) throws Exception {
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(
                PropertiesConfiguration.class).configure(new Parameters().properties()
                .setFileName(fileName)
                .setEncoding(ENCODING)
                .setListDelimiterHandler(new DefaultListDelimiterHandler(LIST_DELIMITER))
                .setThrowExceptionOnMissing(THROW_EXCEPTION_ON_MISSING)
                .setIncludesAllowed(INCLUDES_ALLOWED));
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException ex) {
            throw new Exception("Unable to get the Configuration file: " + ex.getMessage());
        }
    }
}