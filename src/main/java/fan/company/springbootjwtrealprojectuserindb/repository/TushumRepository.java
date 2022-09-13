package fan.company.springbootjwtrealprojectuserindb.repository;


import fan.company.springbootjwtrealprojectuserindb.entity.Tushumlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface TushumRepository extends JpaRepository<Tushumlar, Long> {

    @Query("select count(t.tushum) from Tushumlar t")
    Double countByTushum();

    Double countByCreateAtBetween(Timestamp start, Timestamp end);

    @Query(value = "SELECT SUM(tushum) FROM Tushumlar", nativeQuery = true)
    Double getAllTushum();


    @Query(value = "select SUM(t.tushum) from Tushumlar t where t.createAt between ?1 and ?2")
    Double sumTushumlarByCreateAtBetween(Timestamp start, Timestamp end);



}
