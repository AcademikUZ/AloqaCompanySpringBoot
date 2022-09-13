package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.PrefixAndCode;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomPrefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "prefix", collectionResourceRel = "list", excerptProjection = CustomPrefix.class)
public interface PrefixRepository extends JpaRepository<PrefixAndCode, Long> {

}
