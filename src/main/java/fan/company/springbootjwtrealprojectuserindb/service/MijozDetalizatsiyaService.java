package fan.company.springbootjwtrealprojectuserindb.service;

import fan.company.springbootjwtrealprojectuserindb.entity.Harakat;
import fan.company.springbootjwtrealprojectuserindb.entity.Mijoz;
import fan.company.springbootjwtrealprojectuserindb.entity.MijozDetalizatsiya;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.MijozDetalizatsiyaDto;
import fan.company.springbootjwtrealprojectuserindb.payload.TimeStampDto;
import fan.company.springbootjwtrealprojectuserindb.repository.HarakatRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.MijozDetalizatsiyaRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.MijozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MijozDetalizatsiyaService {


    @Autowired
    MijozDetalizatsiyaRepository repository;
    @Autowired
    HarakatRepository harakatRepository;
    @Autowired
    MijozRepository mijozRepository;

    public Page<MijozDetalizatsiya> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public MijozDetalizatsiya getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<MijozDetalizatsiya> getAllBetween(Long id, TimeStampDto dto) {
        Mijoz userInSystem = (Mijoz) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Mijoz> optionalMijoz = mijozRepository.findById(id);
        if (!optionalMijoz.isPresent()) {
            return new ArrayList<>();
        }
        if (userInSystem.getId() == optionalMijoz.get().getId()) {
            return repository.findAllByCreatedAtBetween(dto.getStart(), dto.getEnd());
        }
        return new ArrayList<>();

    }

    public ApiResult add(MijozDetalizatsiyaDto dto) {

        try {
            Optional<Mijoz> optionalMijoz = mijozRepository.findById(dto.getMijozId());

            if (!optionalMijoz.isPresent()) {
                return new ApiResult("Bunday Mijoz mavjud emas", false);
            }

            Optional<Harakat> optionalHarakat = harakatRepository.findById(dto.getHarakatId());

            if (!optionalHarakat.isPresent()) {
                return new ApiResult("Bunday Harakat mavjud emas", false);
            }

            MijozDetalizatsiya detalizatsiya = new MijozDetalizatsiya();
            detalizatsiya.setMijoz(optionalMijoz.get());
            detalizatsiya.setHarakat(optionalHarakat.get());

            repository.save(detalizatsiya);
            return new ApiResult("MijozDetalizatsiya saqlandi", true);

        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }

    public ApiResult delete(Long id) {

        try {
            boolean existsById = repository.existsById(id);
            if (!existsById) {
                return new ApiResult("Bunday MijozDetalizatsiya mavjud emas!", false);
            }
            repository.deleteById(id);
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
