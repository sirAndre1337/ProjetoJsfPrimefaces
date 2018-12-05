package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Permi��es de acessos do usuario. (S� ele pode editar um bairro,excluir um bairro,ter
 * acesso a parte financeira, ter acesso as mensagems do sistema etc).
 * v : 11.
 * @author Andr� Luis
 *
 */
public enum Permissao {

	ADMIN("ADMIN", "administrador"), USER("USER", "Usu�rio Padr�o"),
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),
	FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR", "Financeiro - Acessar"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "Mensagem recebida - Acessar"),

	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "Bairro - Acessar"),
	BAIRRO_NOVO("BAIRRO_NOVO", "Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR", "Bairro - Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR", "Bairro - Excluir");

	private String valor = "";
	private String descricao = "";

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	private Permissao() {

	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return getValor();
	}

	public static List<Permissao> getListPermissao() {
		List<Permissao> permissaos = new ArrayList<Permissao>();
		for (Permissao permissao : Permissao.values()) {
			permissaos.add(permissao);
		}
		Collections.sort(permissaos, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal())); // ordena a lista de permi�oes.
			}
		});
		return permissaos;
	}
}
