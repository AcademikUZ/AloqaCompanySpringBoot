package fan.company.springbootjwtrealprojectuserindb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarifReja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String tarif;

    @NotNull
    private Double abonentTulov;

    @NotNull
    private Double utishNarxi;

    @NotNull
    private Date amalQilishMuddati;

    @NotNull
    private Double limitDaqiqa;

    @NotNull
    private Double limitMB;

    @NotNull
    private Double limitSMS;

    @NotNull
    private Double limitTugagandagiNarxDaqiqa;

    @NotNull
    private Double limitTugagandagiNarxMB;

    @NotNull
    private Double limitTugagandagiNarxSMS;

    @NotNull
    private boolean active;

    @NotNull
    private String izoh;

}
