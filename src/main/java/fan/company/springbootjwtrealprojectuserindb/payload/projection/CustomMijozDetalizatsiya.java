package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.Lavozim;
import fan.company.springbootjwtrealprojectuserindb.entity.MijozDetalizatsiya;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = MijozDetalizatsiya.class)
public interface CustomMijozDetalizatsiya {

    public Long getId();

    public String getMijoz();

    public String getHarakat();

}
