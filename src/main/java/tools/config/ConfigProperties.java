package tools.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ConfigProperties implements IConfig {

    @Override
    public Map<String, String> getConfig() {
        Map<String, String> props = new HashMap<String, String>();
        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                props.put(entry.getKey().toString(), entry.getValue().toString());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return props;
    }
}
