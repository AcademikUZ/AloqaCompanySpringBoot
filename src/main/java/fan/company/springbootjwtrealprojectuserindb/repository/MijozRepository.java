package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.Mijoz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MijozRepository extends JpaRepository<Mijoz, Long> {

    boolean existsByPasportSeriya(String pasportSeriya);

    Long countByXizmatlar_Id(Long xizmatlar_id);

    Long countByTarifReja_Id(Long tarifReja_id);

}
