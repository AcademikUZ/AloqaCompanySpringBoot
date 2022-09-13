package fan.company.springbootjwtrealprojectuserindb.payload;

import fan.company.springbootjwtrealprojectuserindb.entity.Mijoz;
import fan.company.springbootjwtrealprojectuserindb.entity.TulovTuri;
import fan.company.springbootjwtrealprojectuserindb.entity.Xizmatlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TushumlarDto {

    @NotNull
    private Long xizmatlarId;

    @NotNull
    private Long mijozId;

    @NotNull
    private Double tushum;

    @NotNull
    private Long tulovTuriId;

}
