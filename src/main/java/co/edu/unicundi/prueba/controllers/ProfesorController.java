package co.edu.unicundi.prueba.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.unicundi.prueba.services.IProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import co.edu.unicundi.prueba.entity.Profesor;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.exception.ParamRequiredException;
import co.edu.unicundi.prueba.exception.UnsupportedMediaTypeStatusException;

@RestController
@RequestMapping("/profesores")
@Api(tags = "ProfesorController", value = "Microservicios Profesor", description = "Esta API es un CRUD para Profesores")
public class ProfesorController {

	@Autowired
	private IProfesorService profesorService;

	/*
	@GetMapping("/llenar")
	@ApiOperation(value = "Llenar lista profesores", notes = "Este metodo llena la lista Profesores")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Forbidden"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })
	public String llenar() {

		String mensaje = profesorService.llenar();
		return mensaje;
	}
	 */

	@GetMapping("/listar")
	@ApiOperation(value = "Listar Profesores", notes = "Este metodo retorna una lista de profesores")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Forbidden"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })
	public ResponseEntity<?> listar() throws ModelNotFoundException {
		ArrayList<Profesor> lista = (ArrayList<Profesor>) profesorService.listar();
		return new ResponseEntity<List<Profesor>>(lista,HttpStatus.OK);
	}

	@GetMapping("/retornarId2/{id}")
	public ResponseEntity<?> retornarId2(
			 @PathVariable Integer id)
			throws ModelNotFoundException {
		
		Profesor profesor = profesorService.retornarId2(id);
		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}
	
	@GetMapping("/retornarId3/{id}")
	public ResponseEntity<?> retornarId3(
			@ApiParam(name = "id", type = "Integer", value = "Id dado al Usuario", example = "1", required = true) @PathVariable Integer id)
			throws ModelNotFoundException {
		
		Profesor profesor = profesorService.retornarId3(id);
		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}
	
	
	@GetMapping("/retornarId/{id}")
	@ApiOperation(value = "Retornar Id del Profesor", notes = "Este metodo retorna un profesor por su id o identificador")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Forbidden"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })
	public ResponseEntity<?> retornarId(
			@ApiParam(name = "id", type = "Integer", value = "Id dado al Usuario", example = "1", required = true) @PathVariable Integer id)
			throws ModelNotFoundException {
		
		Profesor profesor = profesorService.retornarId(id);
		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}

	@PostMapping(value="/guardar",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Guardar Profesor", notes = "Este metodo guarda los profesores")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request"),
			@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "Conflict"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })
	public ResponseEntity<?> guardar(
			@ApiParam(value = "Clase tipo Profesor", required = true)@RequestBody Profesor profesor)
			throws ModelNotFoundException, ParamRequiredException,UnsupportedMediaTypeStatusException {

		String mensaje = profesorService.guardar(profesor);
		return new ResponseEntity<String>(mensaje, HttpStatus.CREATED);
	}

	@PutMapping("/editar")
	@ApiOperation(value = "Editar Profesor", notes = "Este metodo edita un profesor por su id o identificador")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })
	public ResponseEntity<?> editar(
			@ApiParam(value = "Clase tipo Profesor", required = true) @Valid @RequestBody Profesor profesor)
			throws ModelNotFoundException, ParamRequiredException {
		String mensaje = profesorService.editar(profesor);
		if (mensaje.equals("datos vacios") || mensaje.equals("Cedula no encontrada")) {
			return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(mensaje, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminar/{id}")
	@ApiOperation(value = "Eliminar Profesor", notes = "Este metodo elimina un profesor por su id o identificador")
	@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "No Content"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Forbidden"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Inernal Server Error") })

	public ResponseEntity<?> eliminar(
			@ApiParam(name = "id", type = "Integer", value = "Id dado al Usuario", example = "1", required = true) @PathVariable Integer id) throws ModelNotFoundException {
		String mensaje = profesorService.eliminar(id);
		/*if (mensaje.equals("No se encontro el Profesor")) {
			return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
		}*/
		return new ResponseEntity<String>(mensaje, HttpStatus.NO_CONTENT);
	}

}
