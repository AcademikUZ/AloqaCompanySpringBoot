package fan.company.springbootjwtrealprojectuserindb.payload;

import fan.company.springbootjwtrealprojectuserindb.entity.PrefixAndCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimKartaDto {

    @NotNull
    private Long prefixAndCodeId;

    @Size(min = 7, max = 7)
    @NotNull
    private Long nomer;

}
