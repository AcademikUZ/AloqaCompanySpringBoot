package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.TarifReja;
import fan.company.springbootjwtrealprojectuserindb.payload.projection.CustomTarifReja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tarif", collectionResourceRel = "list", excerptProjection = CustomTarifReja.class)
public interface TarifRejaRepository extends JpaRepository<TarifReja, Long> {
}
