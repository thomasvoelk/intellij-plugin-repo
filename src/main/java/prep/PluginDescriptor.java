package prep;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PluginDescriptor {
    private String id;
    private String version;
    @JacksonXmlProperty(localName = "idea-version")
    private IdeaVersion ideaVersion;
}
