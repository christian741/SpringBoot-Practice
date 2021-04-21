package co.edu.unicundi.prueba.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;

public interface IDetalleConsultaService {
	
	public DetalleConsulta retornarPorId(int id) throws ModelNotFoundException;
	
	 public Page<DetalleConsulta> paginas(Pageable pageable);
	
	public List<DetalleConsulta> retornar();
	
	
	public void editar(DetalleConsulta detalle) throws ModelNotFoundException;
	
	public void eliminar(Integer id) throws ModelNotFoundException;

}
