package co.edu.unicundi.prueba.services;

import java.util.List;

import co.edu.unicundi.prueba.entity.Profesor;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.exception.ParamRequiredException;
import co.edu.unicundi.prueba.exception.UnsupportedMediaTypeStatusException;

public interface IProfesorService {
	/*
	public String llenar();

	public List<ProfesorDto> listar() throws ModelNotFoundException;
	
	public ProfesorDto retornarId(Integer id) throws ModelNotFoundException;
	
	public String guardar(ProfesorDto profesor) throws ModelNotFoundException, ParamRequiredException,UnsupportedMediaTypeStatusException;
	
	public String editar(ProfesorDto profesor) throws ModelNotFoundException, ParamRequiredException;
	
	public String eliminar(Integer id) throws ModelNotFoundException;*/
	

	public List<Profesor> listar() throws ModelNotFoundException;
	
	public Profesor retornarId(Integer id) throws ModelNotFoundException;
	public Profesor retornarId2(Integer id) throws ModelNotFoundException;
	public Profesor retornarId3(Integer id) throws ModelNotFoundException;
	
	public String guardar(Profesor profesor) throws ModelNotFoundException, ParamRequiredException,UnsupportedMediaTypeStatusException;
	
	public String editar(Profesor profesor) throws ModelNotFoundException, ParamRequiredException;
	
	public String eliminar(Integer id) throws ModelNotFoundException;
}
