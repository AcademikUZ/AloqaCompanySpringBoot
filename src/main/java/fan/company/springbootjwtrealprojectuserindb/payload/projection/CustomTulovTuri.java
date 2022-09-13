package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.Lavozim;
import fan.company.springbootjwtrealprojectuserindb.entity.TulovTuri;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = TulovTuri.class)
public interface CustomTulovTuri {

    public Long getId();

    public String getName();

    public String getIzoh();

}
