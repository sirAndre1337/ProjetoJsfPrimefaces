package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

public class SrvCidadeImpl implements SrvCidade{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryCidade repositoryCidade;
	
	
}
