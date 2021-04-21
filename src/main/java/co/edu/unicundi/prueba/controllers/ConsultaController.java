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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.prueba.entity.Consulta;
import co.edu.unicundi.prueba.entity.DetalleConsulta;
import co.edu.unicundi.prueba.exception.ModelNotFoundException;
import co.edu.unicundi.prueba.services.IConsultaService;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {
	
	@Autowired
	private IConsultaService service;
	
	@GetMapping("/retornar")
	public ResponseEntity<?> retornar() throws ModelNotFoundException  {
			List<Consulta> listaConsulta = service.retornar();
			return new ResponseEntity<List<Consulta>>(listaConsulta, HttpStatus.OK);			
	}	
	
	@GetMapping("/paginas")
    public ResponseEntity<?> paginas(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size,
    		@RequestParam(defaultValue = "id") String order,
    		@RequestParam(defaultValue = "true") boolean asc
    ){
        Page<Consulta> consultas = service.paginas(PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
        	consultas = service.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }

	
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
			Consulta consulta = service.retornarPorId(id);
			return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);			
	}	
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Consulta consulta) {	
			service.guardar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody Consulta consulta) throws ModelNotFoundException {	
			service.editar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> elimianr(@PathVariable int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	
	
	@GetMapping("/detalleConsulta")
    public ResponseEntity<?> detalleConsulta(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size,
    		@RequestBody DetalleConsulta detalle
    ){
        Page<Consulta> consultas = service.buscarDetalleConsulta(page, size,detalle);
         return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }
	
	@GetMapping("/ascendente")
    public ResponseEntity<?> ascendente(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size
    ){
        Page<Consulta> consultas = service.ordenAscendente(page,size);
         return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }
	
	@GetMapping("/descendente")
    public ResponseEntity<?> descendente(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size
    ){
        Page<Consulta> consultas = service.ordenDescendente(page, size);
         return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }
	
	@GetMapping("/busquedaAnd/{id}")
    public ResponseEntity<?> busquedaAnd(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size,
    		@RequestParam(defaultValue = "david") String nombreDoctor,
    		@PathVariable Integer id
    ){
        Page<Consulta> consultas = service.busquedaAnd(page, size, nombreDoctor, id);
         return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }
	
	@GetMapping("/busquedaOr/{id}")
    public ResponseEntity<?> busquedaOr(
    		@RequestParam(defaultValue  = "0") int page,
    		@RequestParam(defaultValue = "10") int size,
    		@RequestParam(defaultValue = "david") String nombreDoctor,
    		@PathVariable Integer id
    ){
        Page<Consulta> consultas = service.busquedaOr(page, size,nombreDoctor, id);
         return new ResponseEntity<Page<Consulta>>(consultas, HttpStatus.OK);
    }


}
