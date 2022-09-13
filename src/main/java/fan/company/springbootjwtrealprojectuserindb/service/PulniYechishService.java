package fan.company.springbootjwtrealprojectuserindb.service;

import fan.company.springbootjwtrealprojectuserindb.entity.Mijoz;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.repository.MijozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PulniYechishService {

    @Autowired
    MijozRepository mijozRepository;


    public ApiResult getDaqiqa(Long mijozId, Long minute){
        Mijoz mijoz = mijozRepository.findById(mijozId).get();
        if (!mijoz.isActive() || mijoz.getLimitDaqiqa() < minute || mijoz.getLimitDaqiqa()<=0) {
            return new ApiResult("Sizda yetarlicha mablag' mavjud emas. Iltimos hisobingizni to'ldiring!", false);
        }
        mijoz.setLimitDaqiqa(mijoz.getLimitDaqiqa() - minute);
        mijozRepository.save(mijoz);
        return new ApiResult("OK!", true);
    }

    public ApiResult getMB(Long mijozId, Double mb){
        Mijoz mijoz = mijozRepository.findById(mijozId).get();
        if (!mijoz.isActive() || mijoz.getLimitMB() < mb || mijoz.getLimitMB()<=0) {
            return new ApiResult("Sizda yetarlicha mablag' mavjud emas. Iltimos hisobingizni to'ldiring!", false);
        }
        mijoz.setLimitMB(mijoz.getLimitMB() - mb);
        mijozRepository.save(mijoz);
        return new ApiResult("OK!", true);
    }

    public ApiResult getSMS(Long mijozId, Long sms){
        Mijoz mijoz = mijozRepository.findById(mijozId).get();
        if (!mijoz.isActive() || mijoz.getLimitSMS() < sms || mijoz.getLimitMB()<=0) {
            return new ApiResult("Sizda yetarlicha mablag' mavjud emas. Iltimos hisobingizni to'ldiring!", false);
        }
        mijoz.setLimitSMS(mijoz.getLimitSMS() - sms);
        mijozRepository.save(mijoz);
        return new ApiResult("OK!", true);
    }

}
