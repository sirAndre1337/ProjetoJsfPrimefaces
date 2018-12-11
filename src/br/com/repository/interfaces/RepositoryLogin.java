package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
/**
 * Interface para autenticar o usuario
 * v : 50
 * @author Andr� Luis
 *
 */
@Repository
public interface RepositoryLogin extends Serializable{

	boolean autentico(String login, String senha) throws Exception;
	
	
	
}
