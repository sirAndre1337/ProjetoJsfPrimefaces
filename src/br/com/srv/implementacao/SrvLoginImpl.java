package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.SvrLogin;

/**
 * Servi�o para autenticar o usuario.
 * v : 51
 * @author Andr� Luis
 *
 */
public class SrvLoginImpl implements SvrLogin{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		return repositoryLogin.autentico(login, senha);
	}

}
