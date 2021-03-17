package prep;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PluginRouter {
    @Bean
    public RouterFunction<ServerResponse> route(PluginController pluginController) {

        return RouterFunctions
                .route(RequestPredicates.POST("/upload").and(RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA)), pluginController::uploadPlugin);
    }
}
