package fan.company.springbootjwtrealprojectuserindb.controller;

import fan.company.springbootjwtrealprojectuserindb.entity.USSD;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.service.PulniYechishService;
import fan.company.springbootjwtrealprojectuserindb.service.USSDService;
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
@RequestMapping("/api/pulniyechish")
public class PulniYechibOlishController {

    @Autowired
    PulniYechishService service;


    @PreAuthorize(value = "hasAnyRole('ROLE_MIJOZ')")
    @PostMapping("/daqiqa/{mijozId}")
    public HttpEntity<?> getDaqiqa (@PathVariable Long mijozId, @RequestBody Long minut){
        ApiResult apiResult = service.getDaqiqa(mijozId, minut);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_MIJOZ')")
    @PostMapping("/trafik/{mijozId}")
    public HttpEntity<?> getMB (@PathVariable Long mijozId, @RequestBody Double mb){
        ApiResult apiResult = service.getMB(mijozId, mb);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_MIJOZ')")
    @PostMapping("/sms/{mijozId}")
    public HttpEntity<?> getSMS (@PathVariable Long mijozId, @RequestBody Long sms){
        ApiResult apiResult = service.getSMS(mijozId, sms);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
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
