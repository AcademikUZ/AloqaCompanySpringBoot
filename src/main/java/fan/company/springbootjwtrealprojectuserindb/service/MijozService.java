package fan.company.springbootjwtrealprojectuserindb.service;

import fan.company.springbootjwtrealprojectuserindb.entity.*;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.MijozDto;
import fan.company.springbootjwtrealprojectuserindb.payload.RegisterDto;
import fan.company.springbootjwtrealprojectuserindb.payload.SimKartaDto;
import fan.company.springbootjwtrealprojectuserindb.repository.*;
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
public class MijozService {


    @Autowired
    MijozRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SimKartaRepository simKartaRepository;
    @Autowired
    PrefixRepository prefixRepository;
    @Autowired
    TarifRejaRepository tarifRejaRepository;
    @Autowired
    XizmatlarRepository xizmatlarRepository;

    /**
     * Barcha Mijozlarni ko'rish uchun
     */
    public Page<Mijoz> getAllMijoz(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    /**
     * Ushbu idlik xizmatga nechta mijoz ulanganini ko'rsatadi
     * @param id
     * @return
     */
    public Long getSortByXizmat(Long id) {
        return repository.countByXizmatlar_Id(id);
    }

    public Long getSortByTarif(Long id) {
        return repository.countByTarifReja_Id(id);
    }

    public Mijoz getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ApiResult addMijoz(MijozDto dto) {

        try {
            boolean existsByPasportSeriya = repository.existsByPasportSeriya(dto.getPasportSeriya());
            if (existsByPasportSeriya)
                return new ApiResult("Bunday Mijoz mavjud", false);

            Mijoz mijoz = new Mijoz();

            if (addMijoz(dto, mijoz)) return new ApiResult("Prefixda xatolik", false);

            return new ApiResult("Saqlandi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


    public ApiResult edit(Long id, MijozDto dto) {

        try {
            Optional<Mijoz> optionalMijoz = repository.findById(id);
            if (!optionalMijoz.isPresent())
                return new ApiResult("Bunday Mijoz mavjud emas", false);

            Mijoz mijoz = optionalMijoz.get();

            if (addMijoz(dto, mijoz)) return new ApiResult("Prefixda xatolik", false);

            return new ApiResult("Taxrirlandi", true);

        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }





    private boolean addMijoz(MijozDto dto, Mijoz mijoz) {
        User userInSystem = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!dto.getSimKartaList().isEmpty()) {
            List<SimKarta> simKartaList = new ArrayList<>();
            for (SimKartaDto simKartaDto : dto.getSimKartaList()) {
                Optional<PrefixAndCode> optionalPrefixAndCode = prefixRepository.findById(simKartaDto.getPrefixAndCodeId());
                if (!optionalPrefixAndCode.isPresent())
                    return true;
                SimKarta simKarta = new SimKarta(
                        optionalPrefixAndCode.get(),
                        simKartaDto.getNomer(),
                        mijoz
                );
                simKartaList.add(simKarta);
            }
            mijoz.setSimKartaList(simKartaList);
        }

        Optional<TarifReja> optionalTarifReja = tarifRejaRepository.findById(dto.getTarifRejaId());
        Optional<Xizmatlar> optionalXizmatlar = xizmatlarRepository.findById(dto.getXizmatlarId());

        mijoz.setPassword(dto.getPassword());
        mijoz.setFullName(dto.getFullName());
        mijoz.setBirthDate(dto.getBirthDate());
        mijoz.setPasportSeriya(dto.getPasportSeriya());
        mijoz.setRoyxatgaOluvchiXodim(userInSystem);
        mijoz.setTarifReja(optionalTarifReja.get());
        mijoz.setJoriyHisob(dto.getJoriyHisob());
        mijoz.setActive(dto.isActive());
        mijoz.setXizmatlar(optionalXizmatlar.orElse(null));
        mijoz.setPaketlarList(dto.getPaketlarList());
        mijoz.setLimitMB(dto.getLimitMB());
        mijoz.setLimitSMS(dto.getLimitSMS());
        mijoz.setLimitDaqiqa(dto.getLimitDaqiqa());

        repository.save(mijoz);
        return false;
    }

    public ApiResult delete(Long id) {

        try {
            boolean existsByMijozName = repository.existsById(id);
            if (existsByMijozName) {
                repository.deleteById(id);
                return new ApiResult("O'chirildi", true);
            }
            return new ApiResult("Bunday Mijoz mavjud emas", false);
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
