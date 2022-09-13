package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.Lavozim;
import fan.company.springbootjwtrealprojectuserindb.entity.Xizmatlar;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomLavozim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "xizmatlar", collectionResourceRel = "list", excerptProjection = CustomLavozim.class)
public interface XizmatlarRepository extends JpaRepository<Xizmatlar, Long> {

}
