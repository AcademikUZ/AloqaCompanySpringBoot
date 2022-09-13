package fan.company.springbootjwtrealprojectuserindb.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeStampDto {

    @NotNull
    private Timestamp start;

    @NotNull
    private Timestamp end;

}
