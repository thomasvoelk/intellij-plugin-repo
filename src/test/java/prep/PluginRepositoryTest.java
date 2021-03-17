package prep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataR2dbcTest
public class PluginRepositoryTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private PluginRepository pluginRepository;

    private R2dbcEntityTemplate template;

    @BeforeEach
    void setUp() throws IOException {
        template = new R2dbcEntityTemplate(databaseClient, H2Dialect.INSTANCE);
        String schemaSql = StreamUtils.copyToString(getClass().getClassLoader().getResourceAsStream("schema.sql"), Charset.defaultCharset());
        template.getDatabaseClient().sql(schemaSql).fetch().rowsUpdated().block();
    }

    @Test
    void name() {
        Plugin plugin = new Plugin(null, "hurz");

        template.insert(Plugin.class).using(plugin).then().as(StepVerifier::create).verifyComplete();

        Flux<Plugin> plugins = pluginRepository.findAll();
        plugins.as(StepVerifier::create).assertNext(
                actual -> {
                    assertThat(actual.getHurz(), is("hurz"));
                }
        );
    }

}