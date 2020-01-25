package sistemasanitario.config;

import java.util.List;
import org.apache.commons.configuration2.PropertiesConfiguration;


public class Config {

    private static final String CONFIG = "config";
    private final PropertiesConfiguration config;
    private final String category;

    public Config(String fileName, String category) throws Exception{
        this.config = ConfigLoader.get(String.format("%s/%s", CONFIG, fileName));
        this.category = category;
    }

    private String formatter(String key) {
        return String.format("%s.%s", category, key);
    }

    protected String getString(String key) {
        return config.getString(formatter(key));
    }

    protected int getInt(String key) {
        return config.getInt(formatter(key));
    }

    protected boolean getBoolean(String key) {
        return config.getBoolean(formatter(key));
    }

    protected double getDouble(String key) {
        return config.getDouble(formatter(key));
    }

    protected short getShort(String key) {
        return config.getShort(formatter(key));
    }

    protected List getList(String key) {
        return config.getList(formatter(key));
    }
    
}
