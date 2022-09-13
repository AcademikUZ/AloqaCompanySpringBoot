package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.PrefixAndCode;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = PrefixAndCode.class)
public interface CustomPrefix {

    public Long getId();

    public Long getPrefixAndCode();

}
