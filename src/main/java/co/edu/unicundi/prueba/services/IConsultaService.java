package co.edu.unicundi.prueba.services;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import co.edu.unicundi.prueba.entity.Consulta;
import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;



public interface IConsultaService {

	public Consulta retornarPorId(int id) throws ModelNotFoundException;
	
	 public Page<Consulta> paginas(Pageable pageable);
	
	public List<Consulta> retornar();
	
	public void guardar(Consulta consulta);
	
	public void editar(Consulta consulta) throws ModelNotFoundException;
	
	public void eliminar(Integer id) throws ModelNotFoundException;
	
	//Otros
	
	 public Page<Consulta> buscarDetalleConsulta(int page, int size,DetalleConsulta detalleConsulta);
	 
	 public Page<Consulta> ordenAscendente(int page, int size);
	 
	 public Page<Consulta> ordenDescendente(int page, int size);
	 
	 public Page<Consulta> busquedaAnd(int page, int size,String  nombreDoctor,Integer id);
	 
	 public Page<Consulta> busquedaOr(int page, int size,String  nombreDoctor,Integer id);
}
