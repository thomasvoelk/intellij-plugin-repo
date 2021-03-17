package prep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Plugin {

    @Id
    private Long id;

    private String hurz;

    //private PluginDescriptor pluginDescriptor;
}
