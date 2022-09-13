package fan.company.springbootjwtrealprojectuserindb.service;

import fan.company.springbootjwtrealprojectuserindb.entity.Filial;
import fan.company.springbootjwtrealprojectuserindb.entity.Oylik;
import fan.company.springbootjwtrealprojectuserindb.entity.OylikPay;
import fan.company.springbootjwtrealprojectuserindb.entity.User;
import fan.company.springbootjwtrealprojectuserindb.payload.*;
import fan.company.springbootjwtrealprojectuserindb.repository.FilialRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.OylikPayRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.OylikRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FilialService {


    @Autowired
    FilialRepository filialRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;

    /**
     * Barcha filiallarni ko'rish uchun
     */
    public Page<Filial> getAllFilial(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return filialRepository.findAll(pageable);
    }

    public Filial getOne(Long id) {
        return filialRepository.findById(id).orElse(null);
    }

    /**
     * Barcha filial hodimlarini ko'rish uchun
     *
     * @param filialId
     * @return
     */
    public List<User> getAllFilialPersonals(Long filialId) {
        return userRepository.findAllByFilial_Id(filialId);
    }

    public ApiResult add(FilialDto filialDto) {

        try {
            boolean existsByFilialName = filialRepository.existsByFilialName(filialDto.getFilialName());
            if (!existsByFilialName) {
                if (!filialDto.getUser().isEmpty()) {
                    for (RegisterDto registerDto : filialDto.getUser()) {
                        authService.register(registerDto);
                    }
                }
                Filial filial = new Filial(filialDto.getFilialName());

                filialRepository.save(filial);

                return new ApiResult("Saqlandi", true);
            }
            return new ApiResult("Bunday filial mavjud", false);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }

    public ApiResult edit(Long id, FilialDto filialDto) {

        try {
            boolean existsByFilialName = filialRepository.existsById(id);
            if (existsByFilialName) {
                if (!filialDto.getUser().isEmpty()) {
                    for (RegisterDto registerDto : filialDto.getUser()) {
                        authService.register(registerDto);
                    }
                }
                Filial filial = new Filial(filialDto.getFilialName());
                filialRepository.save(filial);

                return new ApiResult("Taxrirlandi", true);
            }
            return new ApiResult("Bunday filial mavjud emas", false);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }

    public ApiResult delete(Long id) {

        try {
            boolean existsByFilialName = filialRepository.existsById(id);
            if (existsByFilialName) {
                filialRepository.deleteById(id);
                return new ApiResult("O'chirildi", true);
            }
            return new ApiResult("Bunday filial mavjud emas", false);
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
