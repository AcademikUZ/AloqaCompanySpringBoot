package fan.company.springbootjwtrealprojectuserindb.service;


import fan.company.springbootjwtrealprojectuserindb.entity.*;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.TimeStampDto;
import fan.company.springbootjwtrealprojectuserindb.payload.TushumlarDto;
import fan.company.springbootjwtrealprojectuserindb.payload.RegisterDto;
import fan.company.springbootjwtrealprojectuserindb.repository.MijozRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.TulovTuriRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.TushumRepository;
import fan.company.springbootjwtrealprojectuserindb.repository.XizmatlarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TushumlarService {


    @Autowired
    TushumRepository repository;
    @Autowired
    MijozRepository mijozRepository;
    @Autowired
    XizmatlarRepository xizmatlarRepository;
    @Autowired
    TulovTuriRepository tulovTuriRepository;

    public Page<Tushumlar> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Tushumlar getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Double getTushumlarSoni() {
        return repository.countByTushum();
    }

    public Double getAllTushum() {
        return repository.getAllTushum();
    }

    public Double countByCreateAtBetween(TimeStampDto dto) {
        return repository.countByCreateAtBetween(dto.getStart(), dto.getEnd());
    }

    public Double sumTushumlarByCreateAtBetween(TimeStampDto dto) {
        return repository.sumTushumlarByCreateAtBetween(dto.getStart(), dto.getEnd());
    }

    public ApiResult add(TushumlarDto dto) {

        try {
            Optional<Mijoz> optionalMijoz = mijozRepository.findById(dto.getMijozId());
            Optional<Xizmatlar> optionalXizmatlar = xizmatlarRepository.findById(dto.getXizmatlarId());
            Optional<TulovTuri> optionalTulovTuri = tulovTuriRepository.findById(dto.getTulovTuriId());

            if (!optionalMijoz.isPresent())
                return new ApiResult("Bunday Mijoz mavjud emas!", false);
            if (!optionalXizmatlar.isPresent())
                return new ApiResult("Bunday Xizmat mavjud emas!", false);
            if (!optionalTulovTuri.isPresent())
                return new ApiResult("Bunday To'lov turi mavjud emas!", false);

            Tushumlar tushumlar = new Tushumlar();

            tushumlar.setTushum(dto.getTushum());
            tushumlar.setMijoz(optionalMijoz.get());
            tushumlar.setTulovTuri(optionalTulovTuri.get());
            tushumlar.setXizmatlar(optionalXizmatlar.get());
            repository.save(tushumlar);

            return new ApiResult("Saqlandi", true);

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
