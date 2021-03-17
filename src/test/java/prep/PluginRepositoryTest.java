package prep;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

@DataR2dbcTest
public class PluginRepositoryTest {

    @Autowired
    private PluginRepository pluginRepository;

    @Test
    void name() {
        pluginRepository.findAll();
    }
}
