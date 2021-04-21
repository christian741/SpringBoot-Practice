package co.edu.unicundi.prueba.services.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.repository.IDetalleConsultaRepo;
import co.edu.unicundi.prueba.services.IDetalleConsultaService;

@Service
public class DetalleConsultaSerImp implements IDetalleConsultaService {
	
	@Autowired
	private IDetalleConsultaRepo repo;

	@Override
	public DetalleConsulta retornarPorId(int id) throws ModelNotFoundException {
		DetalleConsulta detalle = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Objeto no Encontrado"));
		return detalle;
	}

	@Override
	public Page<DetalleConsulta> paginas(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public List<DetalleConsulta> retornar() {
		List<DetalleConsulta> listaDetalle = repo.findAll();
		for (DetalleConsulta detalle : listaDetalle) {
			detalle.setConsulta(null);
		}
		return listaDetalle;
	}

	

	@Override
	public void editar(DetalleConsulta detalle) throws ModelNotFoundException {
		
		DetalleConsulta busqueda = this.retornarPorId(detalle.getId());
		if(busqueda==null) {
			throw new ModelNotFoundException("Id erroneo");
		}
		busqueda.setTratamiento(detalle.getTratamiento());
		busqueda.setDiagnostico(detalle.getDiagnostico());
		repo.save(busqueda);
		
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		this.retornarPorId(id);
		repo.deleteById(id);
		
	}

}
