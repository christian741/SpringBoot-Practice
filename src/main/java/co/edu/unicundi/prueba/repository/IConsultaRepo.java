package co.edu.unicundi.prueba.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.prueba.entity.Consulta;
import co.edu.unicundi.prueba.entity.DetalleConsulta;


@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer>,PagingAndSortingRepository<Consulta, Integer> {

	//buscar por detalle consulta
	public Page<Consulta> findByDetalleConsulta(Pageable page,DetalleConsulta detalleConsulta);
	
	public Page<Consulta> findAllByOrderByIdAsc(Pageable page);
	
	public Page<Consulta> findAllByOrderByIdDesc(Pageable page);
	//And
	 public Page<Consulta> findByNombreDoctorAndId(Pageable page,String nombreDoctor,Integer id);
	 //Or
	 public Page<Consulta> findByNombreDoctorOrId(Pageable page,String nombreDoctor,Integer id);
}
