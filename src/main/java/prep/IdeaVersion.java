package prep;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class IdeaVersion {
    @JacksonXmlProperty(localName="since-build", isAttribute = true)
    private String sinceBuild;
    @JacksonXmlProperty(localName="until-build", isAttribute = true)
    private String untilBuild;
}
