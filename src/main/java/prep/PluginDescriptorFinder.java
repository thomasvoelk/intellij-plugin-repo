package prep;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PluginDescriptorFinder {
    public PluginDescriptor find(File plugin) {
        try {
            ZipInputStream xxx = positionStreamAtFile(new FileInputStream(plugin), "META-INF/plugin.xml");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PluginDescriptor pluginDescriptor = xmlMapper.readValue(xxx, PluginDescriptor.class);
            return pluginDescriptor;

        } catch (IOException ex) {
            throw new RuntimeException("unexpected error", ex);
        }
    }

    public static ZipInputStream positionStreamAtFile(InputStream compressedInput, String fileName) throws IOException {
        ZipInputStream input = new ZipInputStream(compressedInput);
        ZipEntry entry = null;
        while ( (entry = input.getNextEntry()) != null ) {
            if(entry.getName().equals(fileName)) {
                return input;
            }
            if (entry.getName().endsWith(".jar")) { // TODO Better checking
                return positionStreamAtFile(input, fileName);
            }
        }
        throw new FileNotFoundException("I could not find the plugin descriptor in the plugin file");
    }
}
