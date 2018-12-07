package br.com.project.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandleFactory extends ExceptionHandlerFactory{

	private ExceptionHandlerFactory parent;
	
	public CustomExceptionHandleFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = new CustomExceptionHandler(parent.getExceptionHandler());
		return handler;
	}

}
