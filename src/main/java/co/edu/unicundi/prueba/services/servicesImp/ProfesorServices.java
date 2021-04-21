package co.edu.unicundi.prueba.services.servicesImp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicundi.prueba.entity.Profesor;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.exception.ParamRequiredException;
import co.edu.unicundi.prueba.exception.UnsupportedMediaTypeStatusException;
import co.edu.unicundi.prueba.repository.IProfesorRepository;
import co.edu.unicundi.prueba.services.IProfesorService;

@Service
public class ProfesorServices implements IProfesorService {
	
	@Autowired 
	private IProfesorRepository repo;

	@Override
	public List<Profesor> listar() throws ModelNotFoundException {
		return repo.findAll();
	}

	@Override
	public Profesor retornarId(Integer id) throws ModelNotFoundException {
		Profesor profesor = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Profesor no exontrado"));
		return profesor;
	}
	
	@Override
	public Profesor retornarId2(Integer id) throws ModelNotFoundException {
		Profesor profesor = repo.findById2(id).orElseThrow(
				() -> new ModelNotFoundException("Profesor no exontrado"));
		return profesor;
	}
	@Override
	public Profesor retornarId3(Integer id) throws ModelNotFoundException {
		Profesor profesor = repo.retornarId3(id);
		return profesor;
	}

	@Override
	public String guardar(Profesor profesor)
			throws ModelNotFoundException, ParamRequiredException, UnsupportedMediaTypeStatusException {
		this.repo.save(profesor);
		return "Guardado";
	}

	@Override
	public String editar(Profesor profesor) throws ModelNotFoundException, ParamRequiredException {
		Profesor pro = this.retornarId(profesor.getId());
		pro.setApellido(profesor.getApellido());
		pro.setNombre(profesor.getNombre());
		pro.setCorreo(profesor.getCorreo());
		pro.setEdad(profesor.getEdad());
		//pro.setCedula(profesor.getCedula());		
		this.repo.save(pro);	
		
		return "editado";
	}

	@Override
	public String eliminar(Integer id) throws ModelNotFoundException {
		Profesor pro = this.retornarId(id);
		this.repo.delete(pro);
		return "Eliminado";
	}


	/*private static ArrayList<ProfesorDto> listaProfesores = new ArrayList<>();

	@Override
	public String llenar() {
		try {

			ProfesorDto prof1 = new ProfesorDto(listaProfesores.size() + 1, "1073179018", "david", "diaz");
			listaProfesores.add(prof1);
			ProfesorDto prof2 = new ProfesorDto(listaProfesores.size() + 1, "1234567", "camilo", "medina");
			listaProfesores.add(prof2);
			ProfesorDto prof3 = new ProfesorDto(listaProfesores.size() + 1, "123121321", "johans", "profe");
			listaProfesores.add(prof3);
			ProfesorDto prof4 = new ProfesorDto(listaProfesores.size() + 1, "9999999", "prueba2", "prueba2");
			listaProfesores.add(prof4);
			ProfesorDto prof5 = new ProfesorDto(listaProfesores.size() + 1, "281281", "prueba1", "prueba1");
			listaProfesores.add(prof5);

		} catch (Exception e) {
			System.out.println(e);
		}
		return "Hecho";
	}

	@Override
	public List<ProfesorDto> listar() throws ModelNotFoundException {
		return listaProfesores;
	}

	@Override
	public ProfesorDto retornarId(Integer id) throws ModelNotFoundException {
		ProfesorDto profesor = new ProfesorDto();

		for (ProfesorDto objeto : listaProfesores) {
			System.out.println(objeto.getCedula());
			if (objeto.getId() == id) {
				profesor = objeto;

			}
		}
		if (profesor.getCedula() == null) {
			throw new ModelNotFoundException("Profesor no Encontrado");
		}

		return profesor;
	}

	@Override
	public String guardar(ProfesorDto profesor) throws ModelNotFoundException, ParamRequiredException {

		profesor.setId(listaProfesores.size() + 1);
		if (profesor.getCedula().equals("") || profesor.getNombre().equals("") || profesor.getApellido().equals("")) {
			throw new ParamRequiredException("Datos Erroneos");
		}
		if (!this.validarNumerico(profesor.getCedula())) {
			throw new ParamRequiredException("cedula es numerica");
		}
		ProfesorDto validarProfesor = buscarId(profesor.getId());
		if (validarProfesor.getId() != null) {
			throw new ParamRequiredException("Lo siento id repetido");
		} else {
			ProfesorDto validarProfesor2 = obtenerCedula(profesor);
			if (validarProfesor2.getId() != null) {
				throw new ParamRequiredException("Lo siento cedula ya registrada");
			} else {
				try {
					listaProfesores.add(profesor);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return "Guardado Correctamente";

	}

	@Override
	public String editar(ProfesorDto profesor) throws ModelNotFoundException, ParamRequiredException {
		if (profesor.getId() == 0 || profesor.getCedula().equals("") || profesor.getNombre().equals("")
				|| profesor.getApellido().equals("")) {
			throw new ParamRequiredException("Datos Erroneos");
		} else {
			ProfesorDto profesorEdit = this.buscarId(profesor.getId());
			if (profesorEdit.getId() == null) {
				throw new ModelNotFoundException("Id no encontrado");
			} else {
				listaProfesores.remove(profesorEdit);
				listaProfesores.add(profesor);
				return "editado correctamente";
			}
		}

	}

	@Override
	public String eliminar(Integer id) throws ModelNotFoundException {

		ProfesorDto profesor = this.buscarId(id);
		if (profesor.getId() == null) {
			throw new ModelNotFoundException("Profesor no encontrado");
		}
		try {
			listaProfesores.remove(profesor);

		} catch (Exception e) {
			System.out.println(e);
		}
		return "Eliminado exitosamente";

	}

	public ProfesorDto obtenerCedula(ProfesorDto profesor) {

		ProfesorDto retornar = new ProfesorDto();
		for (ProfesorDto objeto : listaProfesores) {
			System.out.println(profesor.getCedula());
			System.out.println(objeto.getCedula());
			if (objeto.getCedula().equals(profesor.getCedula())) {
				retornar = objeto;
			}
		}

		return retornar;
	}

	private ProfesorDto buscarId(Integer id) {
		ProfesorDto profesor = new ProfesorDto();

		for (ProfesorDto objeto : listaProfesores) {
			System.out.println(objeto.getCedula());
			if (objeto.getId() == id) {
				profesor = objeto;

			}
		}

		return profesor;
	}

	private Boolean validarNumerico(String valor) {
		try {
			Integer.parseInt(valor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/

}
