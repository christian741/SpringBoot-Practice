package co.edu.unicundi.prueba.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.prueba.entity.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Integer>{
	
	//SQL
	@Query(value = "SELECT * FROM profesor WHERE id=:id", nativeQuery=true)
    Optional<Profesor> findById(Integer id);
	
	//JPQL
	@Query("select p from Profesor p where id=:id")
	Optional<Profesor> findById2(@Param("id") Integer id);
	
	
	//Nombre
	
	
	//@Query(name = "Profesor.retornarId3")
	//Optional<Profesor> retornarId3(Integer id);
	Profesor retornarId3(Integer id);

}
