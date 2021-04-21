package co.edu.unicundi.prueba.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.services.IDetalleConsultaService;

@RestController
@RequestMapping("/detalleConsulta")
public class DetalleConsultaController {
	
	@Autowired
	private IDetalleConsultaService service;
	
	@GetMapping("/retornar")
	public ResponseEntity<?> retornar() throws ModelNotFoundException  {
			List<DetalleConsulta> listaConsulta = service.retornar();
			return new ResponseEntity<List<DetalleConsulta>>(listaConsulta, HttpStatus.OK);			
	}	
	
	@GetMapping("/paginas")
    public ResponseEntity<?> paginas(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size,
    		@RequestParam(defaultValue = "id") String order,
    		@RequestParam(defaultValue = "true") boolean asc
    ){
        Page<DetalleConsulta> consultas = service.paginas(PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
        	consultas = service.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<DetalleConsulta>>(consultas, HttpStatus.OK);
    }

	
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
		DetalleConsulta consulta = service.retornarPorId(id);
			return new ResponseEntity<DetalleConsulta>(consulta, HttpStatus.OK);			
	}	
	
	
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody DetalleConsulta consulta) throws ModelNotFoundException {	
			service.editar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> elimianr(@PathVariable int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	

}
