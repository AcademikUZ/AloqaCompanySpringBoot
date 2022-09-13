package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.PrefixAndCode;
import fan.company.springbootjwtrealprojectuserindb.entity.SimKarta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimKartaRepository extends JpaRepository<SimKarta, Long> {

    boolean existsByNomerAndPrefixandcode_Id(Long nomer, Long prefixandcode_id);


}
