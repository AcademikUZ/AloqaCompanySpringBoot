package fan.company.springbootjwtrealprojectuserindb.controller;

import fan.company.springbootjwtrealprojectuserindb.entity.Mijoz;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.MijozDto;
import fan.company.springbootjwtrealprojectuserindb.service.MijozService;
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
@RequestMapping("/api/mijoz")
public class MijozController {

    @Autowired
    MijozService service;


    @PreAuthorize(value =  "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER', 'ROLE_XODIM')")
    @PostMapping
    public HttpEntity<?> add (@Valid @RequestBody MijozDto dto){
        ApiResult apiResult = service.addMijoz(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER', 'ROLE_XODIM')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit (@Valid @PathVariable Long id, @RequestBody MijozDto dto){
        ApiResult apiResult = service.edit(id, dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER', 'ROLE_XODIM')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne (@PathVariable Long id){
        Mijoz one = service.getOne(id);
        return ResponseEntity.status(one != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(one);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER')")
    @GetMapping("/getSortByTarif/{id}")
    public HttpEntity<?> getSortByTarif (@PathVariable Long id){
        Long sortByTarif = service.getSortByTarif(id);
        return ResponseEntity.status(sortByTarif != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(sortByTarif);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER')")
    @GetMapping("/getSortByXizmat/{id}")
    public HttpEntity<?> getSortByXizmat (@PathVariable Long id){
        Long sortByXizmat = service.getSortByXizmat(id);
        return ResponseEntity.status(sortByXizmat != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(sortByXizmat);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_HR_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping
    public ResponseEntity<?> getAllMijoz(@RequestParam Integer page) {
        Page<Mijoz> all = service.getAllMijoz(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', " +
            "'ROLE_HR_MANAGER', " +
            "'ROLE_FILIALLARNI_BOSHQARISH_MANAGER', " +
            "'ROLE_FILIAL_MANAGER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete (@PathVariable Long id){
        ApiResult apiResult = service.delete(id);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
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
