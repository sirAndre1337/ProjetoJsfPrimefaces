package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;
/**
 * Servi�o para autenticar o usuario.
 * v : 51
 * @author Andr� Luis
 *
 */
@Service
public interface SvrLogin extends Serializable{
	
	boolean autentico(String login, String senha) throws Exception;
	
}
