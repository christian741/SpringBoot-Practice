package co.edu.unicundi.prueba.exception;

import java.util.List;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaTypeStatusException extends org.springframework.web.server.UnsupportedMediaTypeStatusException {

	public UnsupportedMediaTypeStatusException(MediaType contentType, List<MediaType> supportedTypes) {
		super(contentType, supportedTypes);
		// TODO Auto-generated constructor stub
	}
		 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
