package fan.company.springbootjwtrealprojectuserindb.payload;


import fan.company.springbootjwtrealprojectuserindb.entity.Paketlar;
import fan.company.springbootjwtrealprojectuserindb.entity.SimKarta;
import fan.company.springbootjwtrealprojectuserindb.entity.TarifReja;
import fan.company.springbootjwtrealprojectuserindb.entity.Xizmatlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MijozDto {


    @NotNull
    private String password;

    @NotNull
    private Long royxatgaOluvchiXodimId;

    @NotNull
    private List<SimKartaDto> simKartaList;  //Username

    @NotNull
    private String fullName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String pasportSeriya;

    @NotNull
    private Long tarifRejaId;

    @NotNull
    private Double joriyHisob;

    @NotNull
    private boolean active;


    private Long xizmatlarId;

    private List<Paketlar> paketlarList;

    @NotNull
    private Double limitDaqiqa;

    @NotNull
    private Double limitMB;

    @NotNull
    private Double limitSMS;


}
