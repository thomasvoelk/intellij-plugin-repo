package prep;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class IdeaVersion {
    @JacksonXmlProperty(localName="since-build", isAttribute = true)
    private String sinceBuild;
}
