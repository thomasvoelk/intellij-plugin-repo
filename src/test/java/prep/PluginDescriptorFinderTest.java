package prep;

import org.hamcrest.Matchers;
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
 }
