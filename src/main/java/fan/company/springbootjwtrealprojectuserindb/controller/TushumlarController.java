package fan.company.springbootjwtrealprojectuserindb.controller;

import fan.company.springbootjwtrealprojectuserindb.entity.Tushumlar;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.TimeStampDto;
import fan.company.springbootjwtrealprojectuserindb.payload.TushumlarDto;
import fan.company.springbootjwtrealprojectuserindb.service.TushumlarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tushumlar")
public class TushumlarController {

    @Autowired
    TushumlarService service;


    @PostMapping
    public HttpEntity<?> add (@Valid @RequestBody TushumlarDto dto){
        ApiResult apiResult = service.add(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne (@PathVariable Long id){
        Tushumlar one = service.getOne(id);
        return ResponseEntity.status(one != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(one);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/getTushumlarSoni")
    public HttpEntity<?> getTushumlarSoni (){
        Double tushumlarSoni = service.getTushumlarSoni();
        return ResponseEntity.status(tushumlarSoni != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(tushumlarSoni);
    }

    /**
     * Barcha tushumlar
     * @return
     */

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/getAllTushum")
    public HttpEntity<?> getAllTushum (){
        Double tushumlar = service.getAllTushum();
        return ResponseEntity.status(tushumlar != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(tushumlar);
    }

    /**
     * Vaqt oralig'idagi tushumlar soni
     * @return
     */

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/countByCreateAtBetween")
    public HttpEntity<?> countByCreateAtBetween (TimeStampDto dto){
        Double tushumlar = service.countByCreateAtBetween(dto);
        return ResponseEntity.status(tushumlar != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(tushumlar);
    }

    /**
     * Vaqt oralig'idagi tushumlar miqdori
     * @return
     */

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/sumTushumlarByCreateAtBetween")
    public HttpEntity<?> sumTushumlarByCreateAtBetween (TimeStampDto dto){
        Double tushumlar = service.sumTushumlarByCreateAtBetween(dto);
        return ResponseEntity.status(tushumlar != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(tushumlar);
    }


    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam Integer page) {
        Page<Tushumlar> all = service.getAll(page);
        return ResponseEntity.status(!all.isEmpty() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }



    /**
     * javax.validation ga o'zbekcha habar yuvorish uchun kerak
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }





}
