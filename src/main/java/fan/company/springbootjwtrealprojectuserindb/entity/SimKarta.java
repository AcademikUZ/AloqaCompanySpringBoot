package fan.company.springbootjwtrealprojectuserindb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimKarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PrefixAndCode prefixandcode;

    @Column(nullable = false, unique = true)
    private Long nomer;

    @ManyToOne
    private Mijoz mijoz;

    public SimKarta(PrefixAndCode prefixandcode, Long nomer, Mijoz mijoz) {
        this.prefixandcode = prefixandcode;
        this.nomer = nomer;
        this.mijoz = mijoz;
    }
}
