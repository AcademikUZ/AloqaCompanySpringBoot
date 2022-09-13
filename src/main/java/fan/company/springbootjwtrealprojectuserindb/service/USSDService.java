package fan.company.springbootjwtrealprojectuserindb.service;

import fan.company.springbootjwtrealprojectuserindb.entity.USSD;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.repository.USSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class USSDService {


    @Autowired
    USSDRepository ussdRepository;


    public Page<USSD> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ussdRepository.findAll(pageable);
    }

    public USSD getOne(String ussd) {
        return ussdRepository.findByUssd(ussd).orElse(null);
    }

    public ApiResult add(USSD ussd) {

        try {
            boolean existsByUssd = ussdRepository.existsByUssd(ussd.getUssd());
            if (existsByUssd) {
                return new ApiResult("Bunday USSD mavjud", false);
            }
            ussdRepository.save(ussd);
            return new ApiResult("USSD saqlandi", true);

        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }

    public ApiResult edit(Long id, USSD ussd) {

        try {
            boolean existsById = ussdRepository.existsById(id);
            if (!existsById) {
                return new ApiResult("Bunday USSD mavjud emas!", false);
            }
            ussdRepository.save(ussd);
            return new ApiResult("USSD taxrirlandi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }

    public ApiResult delete(Long id) {

        try {
            boolean existsById = ussdRepository.existsById(id);
            if (!existsById) {
                return new ApiResult("Bunday USSD mavjud emas!", false);
            }
            ussdRepository.deleteById(id);
            return new ApiResult("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


//    public Boolean sendMail(String sendingEmail, String message) {
//
//        try {
//
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom("company@fan.uz");
//            simpleMailMessage.setTo(sendingEmail);
//            simpleMailMessage.setSubject("company@fan.uz sizga habar biriktirildi");
//            simpleMailMessage.setText(message);
//            System.out.println(message);
//            javaMailSender.send(simpleMailMessage);
//            return true;
//
//        } catch (MailException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }

}
