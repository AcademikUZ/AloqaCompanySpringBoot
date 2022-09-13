package fan.company.springbootjwtrealprojectuserindb.payload.projection;

import fan.company.springbootjwtrealprojectuserindb.entity.PrefixAndCode;
import fan.company.springbootjwtrealprojectuserindb.entity.TarifReja;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = TarifReja.class)
public interface CustomTarifReja {

    public Long getId();

    public String getTarif();

    public String getIzoh();

    public Double getAbonentTulov();

    public Double getUtishNarxi();

    public Date getAmalQilishMuddati();

    public String getMB_DAQIQA_SMS_VHK();

    public Double getLimitTugagandagiNarxDaqiqa();

    public Double getLimitTugagandagiNarxMB();

    public Double getLimitTugagandagiNarxSMS();

    public boolean isActive();




}
