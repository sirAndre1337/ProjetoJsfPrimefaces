package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * Classe Responsavel por fazer o log do usuario que esta excluindo,inserindo ou editando no BD(Hibernate Envers)
 * @author André Luis
 *
 */
@Component
public class UtilFramework implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	/**
	 * Esse metodo aceita somente uma requisiçao por vez. Se outra requisição tentar acessar
	 * esse metodo enquanto outra esta o usando, ela tera que esperar a 1º requisiçao
	 * se encerrar.
	 * @return
	 */
	public synchronized static ThreadLocal<Long> geThreadLocal(){
		return threadLocal;
	}
	
}
