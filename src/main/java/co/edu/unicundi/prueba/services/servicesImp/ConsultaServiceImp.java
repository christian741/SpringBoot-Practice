package co.edu.unicundi.prueba.services.servicesImp;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicundi.prueba.entity.Consulta;
import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.repository.IConsultaRepo;
import co.edu.unicundi.prueba.services.IConsultaService;




@Service
@Transactional
public class ConsultaServiceImp implements IConsultaService{

	@Autowired
	private IConsultaRepo repo;
	
	@Override
	public Consulta retornarPorId(int id) throws ModelNotFoundException {
		Consulta consulta = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Cosnulta no encontrada"));
		//consulta.setDetalleConsulta(null);
		//consulta.getDetalleConsulta().clear();
		return consulta;
	}

	@Override
	public List<Consulta> retornar() {
		List<Consulta> listaConsulta = repo.findAll();
		for (Consulta consulta : listaConsulta) {
			consulta.setDetalleConsulta(null);
		}
		return listaConsulta;
	}

	@Override
	public void guardar(Consulta consulta) {
		if(consulta.getDetalleConsulta() != null) {
			consulta.getDetalleConsulta().forEach(det -> {
				det.setConsulta(consulta);
			});
		}
		repo.save(consulta);
	}

	@Override
	public void editar(Consulta consulta) throws ModelNotFoundException {
		Consulta con = this.retornarPorId(consulta.getId());
		con.setNombreDoctor(consulta.getNombreDoctor());
		con.setFecha(consulta.getFecha());
		repo.save(con);
	}



	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		//Cambiar por consulta mas optima
		this.retornarPorId(id);
		repo.deleteById(id);
	}

	@Override
	public Page<Consulta> paginas(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Page<Consulta> buscarDetalleConsulta(int page, int size,DetalleConsulta detalleConsulta) {
		return repo.findByDetalleConsulta(PageRequest.of(page, size),detalleConsulta);
	}

	@Override
	public Page<Consulta> ordenAscendente(int page, int size) {
		return repo.findAllByOrderByIdAsc(PageRequest.of(page, size));
	}

	@Override
	public Page<Consulta> ordenDescendente(int page, int size) {
		// TODO Auto-generated method stub
		return repo.findAllByOrderByIdDesc(PageRequest.of(page, size));
	}

	@Override
	public Page<Consulta> busquedaAnd(int page, int size,String nombreDoctor, Integer id) {
		// TODO Auto-generated method stub
		return repo.findByNombreDoctorAndId(PageRequest.of(page, size),nombreDoctor, id);
	}

	@Override
	public Page<Consulta> busquedaOr(int page, int size,String nombreDoctor, Integer id) {
		// TODO Auto-generated method stub
		return repo.findByNombreDoctorOrId(PageRequest.of(page, size),nombreDoctor, id);
	}

	

}
