package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.Filial;
import fan.company.springbootjwtrealprojectuserindb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    boolean existsByFilialName(@NotNull String filialName);

}
