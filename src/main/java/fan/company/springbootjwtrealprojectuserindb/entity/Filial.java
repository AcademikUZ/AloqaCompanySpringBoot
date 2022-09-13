package fan.company.springbootjwtrealprojectuserindb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String filialName;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<User> user;

    public Filial(String filialName) {
        this.filialName = filialName;
    }
}
