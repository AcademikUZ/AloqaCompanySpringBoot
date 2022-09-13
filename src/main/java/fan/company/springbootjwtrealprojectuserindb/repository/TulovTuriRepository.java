package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.TulovTuri;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomTulovTuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tulov_turi", collectionResourceRel = "list", excerptProjection = CustomTulovTuri.class)
public interface TulovTuriRepository extends JpaRepository<TulovTuri, Long> {

}
