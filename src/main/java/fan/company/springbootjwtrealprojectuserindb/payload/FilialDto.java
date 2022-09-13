package fan.company.springbootjwtrealprojectuserindb.payload;

import fan.company.springbootjwtrealprojectuserindb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilialDto {

    @NotNull
    private String filialName;

    private List<RegisterDto> user;

}
