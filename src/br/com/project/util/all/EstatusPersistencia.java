package br.com.project.util.all;

/**
 * Responsavel por carrega as mensagems do sistema
 * v : 16
 * @author André Luis
 *
 */
public enum EstatusPersistencia {

	ERRO("Erro"),
	SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse objeto não pode ser apagado por possuir referências ao mesmo.");
	
	private String name;
	
	private EstatusPersistencia(String s) {
		this.name = s;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
