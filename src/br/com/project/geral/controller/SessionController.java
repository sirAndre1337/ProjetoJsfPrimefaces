package br.com.project.geral.controller;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;
/**
 * Interface para adicionar e invalidar um usuario no sistema.
 * v : 49
 * @author Andr� Luis
 *
 */
@ApplicationScoped
public interface SessionController extends Serializable{

	void addSession(String keyLoginUser, HttpSession httpSession);
	
	void invalidateSession(String keyLoginUser);
	
}
