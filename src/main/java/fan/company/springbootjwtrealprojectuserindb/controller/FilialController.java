package fan.company.springbootjwtrealprojectuserindb.controller;

import fan.company.springbootjwtrealprojectuserindb.entity.Filial;
import fan.company.springbootjwtrealprojectuserindb.entity.User;
import fan.company.springbootjwtrealprojectuserindb.payload.ApiResult;
import fan.company.springbootjwtrealprojectuserindb.payload.FilialDto;
import fan.company.springbootjwtrealprojectuserindb.service.FilialService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/filial")
public class FilialController {

    @Autowired
    FilialService service;


    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping
    public HttpEntity<?> add (@Valid @RequestBody FilialDto dto){
        ApiResult apiResult = service.add(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit (@Valid @PathVariable Long id, @RequestBody FilialDto dto){
        ApiResult apiResult = service.edit(id, dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResult);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_HR_MANAGER', 'ROLE_MANAGER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne (@PathVariable Long id){
        Filial one = service.getOne(id);
        return ResponseEntity.status(one != null ? HttpStatus.OK:HttpStatus.CONFLICT).body(one);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_HR_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping
    public ResponseEntity<?> getAllFilial(@RequestParam Integer page) {
        Page<Filial> all = service.getAllFilial(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_HR_MANAGER', 'ROLE_MANAGER', 'ROLE_FILIALLARNI_BOSHQARISH_MANAGER')")
    @GetMapping("/getAllFilialPersonals/{filial_id}")
    public ResponseEntity<?> getAllFilialPersonals(@PathVariable Long id) {
        List<User> all = service.getAllFilialPersonals(id);
        return ResponseEntity.status(!all.isEmpty() ? HttpStatus.OK : HttpStatus.CONFLICT).body(all);
    }


    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
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
