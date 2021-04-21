package co.edu.unicundi.prueba.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Profesor Esta Clase permite modelar el objeto JSON profesor") 
public class ProfesorDto {
	
	
	@Min(value = 1, message = "Debe almenos tener 1 identificador")
	@ApiModelProperty(
			position = 0,
			value = "Id es el Identificador del Usuario",
			name = "id",
			dataType = "Integer",
			example = "1",
			notes = "Id es el identificador",required = true)
	private Integer id;
	
	@NotNull
	@Size(min = 9, max = 11,message = "Cedula debe tener entre 9 a 11 caracteres")
	@ApiModelProperty(
			position = 1,
			value = "Cedula es Unica e  Identifica al Usuario",
			name = "cedula",
			dataType = "String",
			example = "1073179018",
			notes = "Cedula es unica y tambien identifica al Profesor",required = true)
	private String cedula;
	
	@NotNull
	@Size(min = 3, max = 30,message = "Nombre debe tener entre 3 a 30 caracteres")
	@ApiModelProperty(
			position = 2,
			value = "Nombre del Profesor",
			name = "nombre",
			dataType = "String",
			example = "Christian",
			notes = "Nombre del Profesor")
	private String nombre;
	
	@NotNull
	@Size(min = 4, max = 30,message = "Apellido debe tener entre 4 a 30 caracteres")
	@ApiModelProperty(
			position = 3,
			value = "Apellido del Profesor",
			name = "apellido",
			dataType = "String",
			example = "Diaz",
			notes = "Apellido del Profesor")
	private String apellido;
	
	public ProfesorDto() {
		
	}
	
	public ProfesorDto(Integer id, String cedula, String nombre, String apellido) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	@ApiModelProperty(value = "Retorna Id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ApiModelProperty(value = "Retorna Cedula")
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	@ApiModelProperty(value = "Retorna Nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@ApiModelProperty(value = "Retorna Apelido")
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
