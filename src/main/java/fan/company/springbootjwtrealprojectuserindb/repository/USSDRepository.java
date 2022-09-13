package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.USSD;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface USSDRepository extends JpaRepository<USSD, Long> {

    Optional<USSD> findByUssd(@NotNull String ussd);

    boolean existsByUssd(@NotNull String ussd);

}
