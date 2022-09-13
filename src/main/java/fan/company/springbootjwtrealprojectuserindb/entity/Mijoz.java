package fan.company.springbootjwtrealprojectuserindb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mijoz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName; // Firma yoki mijoznini ismi

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String pasportSeriya;

    @ManyToOne
    private User royxatgaOluvchiXodim;

    @OneToMany(mappedBy = "mijoz", cascade = CascadeType.ALL)
    private List<SimKarta> simKartaList;


    @ManyToOne
    private TarifReja tarifReja;

    @NotNull
    private Double limitDaqiqa;

    @NotNull
    private Double limitMB;

    @NotNull
    private Double limitSMS;

    private Double joriyHisob;

    @NotNull
    private boolean active;

    @ManyToOne
    private Xizmatlar xizmatlar;

    @Transient
    private Integer age;



    public Integer getAge() {
        Integer age = Period.between(this.birthDate, LocalDate.now()).getYears();
        return age;
    }

    @ManyToMany
    private List<Paketlar> paketlarList;

}
