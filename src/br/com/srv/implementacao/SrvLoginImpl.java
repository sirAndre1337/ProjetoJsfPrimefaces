package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.SvrLogin;

/**
 * Serviço para autenticar o usuario.
 * v : 51
 * @author André Luis
 *
 */
@Service
public class SrvLoginImpl implements SvrLogin{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		repositoryLogin.autentico(login, senha);
		return false;
	}

}
