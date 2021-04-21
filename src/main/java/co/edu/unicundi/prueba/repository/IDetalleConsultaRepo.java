package co.edu.unicundi.prueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.prueba.entity.Consulta;
import co.edu.unicundi.prueba.entity.DetalleConsulta;



@Repository
public interface IDetalleConsultaRepo extends JpaRepository<DetalleConsulta, Integer>,PagingAndSortingRepository<DetalleConsulta, Integer>{

	public Page<DetalleConsulta> findByConsulta(Pageable page,Consulta consulta);
}
