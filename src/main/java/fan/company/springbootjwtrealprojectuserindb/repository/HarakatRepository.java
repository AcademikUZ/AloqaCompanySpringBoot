package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.Harakat;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomHarakat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "harakat", collectionResourceRel = "list", excerptProjection = CustomHarakat.class)
public interface HarakatRepository extends JpaRepository<Harakat, Long> {

}
