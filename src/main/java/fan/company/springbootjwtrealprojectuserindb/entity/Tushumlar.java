package fan.company.springbootjwtrealprojectuserindb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tushumlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Xizmatlar xizmatlar;

    @ManyToOne
    private Mijoz mijoz;

    private Double tushum;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createAt;

    @ManyToOne
    private TulovTuri tulovTuri;


}
