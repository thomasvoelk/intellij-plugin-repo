package prep;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PluginDescriptorFinderTest {

    @Test
    void findsPluginDescriptorInPluginWithoutDependencies() {
        File plugin = new File(getClass().getClassLoader().getResource("NyanProgressBar.jar").getFile());

        PluginDescriptor pluginDescriptor = new PluginDescriptorFinder().find(plugin);

        assertThat(pluginDescriptor.getId(), is("some.awesome"));
        assertThat(pluginDescriptor.getVersion(), is("1.14"));
        assertThat(pluginDescriptor.getIdeaVersion().getSinceBuild(), is("192"));

    }

    @Test
    void findsPluginDescriptorInPluginDependencies() {
        File plugin = new File(getClass().getClassLoader().getResource("lombok-plugin.zip").getFile());

        PluginDescriptor pluginDescriptor = new PluginDescriptorFinder().find(plugin);

        assertThat(pluginDescriptor.getId(), is("Lombook Plugin"));
        assertThat(pluginDescriptor.getVersion(), is("0.34.1-2019.1"));
        assertThat(pluginDescriptor.getIdeaVersion().getSinceBuild(), is("191.6183"));
        assertThat(pluginDescriptor.getIdeaVersion().getUntilBuild(), is("191.*"));
    }
 }
