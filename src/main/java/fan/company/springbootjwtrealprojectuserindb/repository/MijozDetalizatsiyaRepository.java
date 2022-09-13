package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.MijozDetalizatsiya;
import fan.company.springbootjwtrealprojectuserindb.payload.MijozDetalizatsiyaDto;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomMijozDetalizatsiya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Timestamp;
import java.util.List;

//@RepositoryRestResource(path = "mijoz_detalizatsiya", collectionResourceRel = "list", excerptProjection = CustomMijozDetalizatsiya.class)
public interface MijozDetalizatsiyaRepository extends JpaRepository<MijozDetalizatsiya, Long> {

    List<MijozDetalizatsiya> findAllByCreatedAtBetween(Timestamp start, Timestamp end);

}
