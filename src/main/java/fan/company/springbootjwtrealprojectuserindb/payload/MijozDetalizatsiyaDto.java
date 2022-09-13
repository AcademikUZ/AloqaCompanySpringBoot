package fan.company.springbootjwtrealprojectuserindb.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MijozDetalizatsiyaDto {

    @NotNull
    private Long mijozId;

    @NotNull
    private Long harakatId;

}
