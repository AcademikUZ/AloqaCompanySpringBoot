package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.Lavozim;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Lavozim.class)
public interface CustomLavozim {

    public Long getId();

    public String getLavozim();

}
