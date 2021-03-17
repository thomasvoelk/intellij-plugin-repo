package prep;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PluginRepository extends ReactiveCrudRepository<Plugin, Long> {
}
