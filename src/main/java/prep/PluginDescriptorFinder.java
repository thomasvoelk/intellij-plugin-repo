package prep;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PluginDescriptorFinder {
    public PluginDescriptor find(File plugin) {
        try {
            ZipInputStream zip = seekPluginDescriptor(new FileInputStream(plugin));
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return xmlMapper.readValue(zip, PluginDescriptor.class);
        } catch (IOException ex) {
            throw new RuntimeException("unexpected error", ex);
        }
    }

    /**
     * positions the stream at the beginning of the META-INF/plugin.xml file
     */
    private static ZipInputStream seekPluginDescriptor(InputStream compressedInput) throws IOException {
        ZipInputStream input = new ZipInputStream(compressedInput);
        ZipEntry entry;
        while ((entry = input.getNextEntry()) != null) {
            if (entry.getName().equals("META-INF/plugin.xml")) {
                return input;
            }
            if (entry.getName().endsWith(".jar")) {
                return seekPluginDescriptor(input);
            }
        }
        throw new FileNotFoundException("I could not find the plugin descriptor in the plugin file");
    }
}
