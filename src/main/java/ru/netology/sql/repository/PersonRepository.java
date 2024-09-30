package ru.netology.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.sql.personEnity.PersonEntity;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

//    @Query("SELECT p FROM PersonEntity p WHERE lower(p.cityOfLiving) = lower(:city)")
//    List<PersonEntity> findAllByCityOfLiving(@Param("city") String city);

    @Query("SELECT p FROM PersonEntity p WHERE LOWER(p.name) = LOWER(:name)")
    List<PersonEntity> findByName(@Param("name") String name);


//    @Query("SELECT p FROM PersonEntity p")
//    List<PersonEntity> findAll();
}
