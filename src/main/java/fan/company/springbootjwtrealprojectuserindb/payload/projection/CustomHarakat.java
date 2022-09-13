package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.Harakat;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Harakat.class)
public interface CustomHarakat {

    public Long getId();

    public String getName();

}
