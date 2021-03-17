package prep;


import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.io.File;

@Component
public class PluginController {

    private PluginDescriptorReader pluginDescriptorReader;

    public PluginController(PluginDescriptorReader pluginDescriptorReader) {
        this.pluginDescriptorReader = pluginDescriptorReader;
    }

    public Mono<ServerResponse> uploadPlugin(ServerRequest request) {

        return request.body(BodyExtractors.toMultipartData()).flatMap(p -> {
            FilePart fp = (FilePart) p.toSingleValueMap().get("file");
            File file = new File("/Users/thomas/Development/".concat(fp.filename()));
            fp.transferTo(file).subscribe();
//                        .and(pluginDescriptorReader.read2(file))

            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("UPLOADED BABY!"), String.class);
        });
//                .onErrorResume(throwable ->
//                Mono.just("ERROR")
//                        .flatMap(s -> ServerResponse.ok().bodyValue(s)));
    }
}
