package br.com.project.util.all;

/**
 * Responsavel por carrega as mensagems do sistema
 * v : 16
 * @author Andr� Luis
 *
 */
public enum EstatusPersistencia {

	ERRO("Erro"),
	SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse objeto n�o pode ser apagado por possuir refer�ncias ao mesmo.");
	
	private String name;
	
	private EstatusPersistencia(String s) {
		this.name = s;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
