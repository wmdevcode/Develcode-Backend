package com.educandoweb.couse.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.couse.services.exceptions.CampoJaExisteException;
import com.educandoweb.couse.services.exceptions.CampoVazioException;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ErroNaoMapeadoException;
import com.educandoweb.couse.services.exceptions.ReferenciaInexistenteException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;
import com.educandoweb.couse.services.exceptions.ViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Recurso não encontrado!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Recurso ja existente!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(CampoVazioException.class)
	public ResponseEntity<StandardError> CampoVazioException(CampoVazioException e, HttpServletRequest request){
		String error = "Campo não pode estar vazio.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ErroNaoMapeadoException.class)
	public ResponseEntity<StandardError> ErroNaoMapeadoException(ErroNaoMapeadoException e, HttpServletRequest request){
		String error = "Erro não mapeado. Favor tentar mais tarde, em caso de persistencia contatar a equipe de TI.";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError (Instant.now(), status.value(), error, e.getMessage(), error);
		return ResponseEntity.status(status).body(err);
	}
	

	@ExceptionHandler(ViolationException.class)
	public ResponseEntity<StandardError> ViolationException(ViolationException e, HttpServletRequest request){
		String error = "Existem campos que não podem estar vazios.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError (Instant.now(), status.value(), error, e.getMessage(), error);
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(CampoJaExisteException.class)
	public ResponseEntity<StandardError> CampoJaExisteException(CampoJaExisteException e, HttpServletRequest request){
		String error = "Ja existe um usuário cadastrado com esse ID.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError (Instant.now(), status.value(), error, e.getMessage(), error);
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ReferenciaInexistenteException.class)
	public ResponseEntity<StandardError> ReferenciaInexistenteException(ReferenciaInexistenteException e, HttpServletRequest request){
		String error = "Referencia a recurso que não existe!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError (Instant.now(), status.value(), error, e.getMessage(), error);
		return ResponseEntity.status(status).body(err);
	}
	
}
