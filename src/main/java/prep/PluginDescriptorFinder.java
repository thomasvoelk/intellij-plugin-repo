package prep;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class PluginDescriptorFinder {
    public PluginDescriptor find(File plugin) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{plugin.toURI().toURL()});
            InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/plugin.xml");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PluginDescriptor pluginDescriptor = xmlMapper.readValue(resourceAsStream, PluginDescriptor.class);
            return pluginDescriptor;

        } catch (IOException ex) {
            throw new RuntimeException("unexpected error", ex);
        }
    }
}
