package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable{

	// Salva os dados usando o metodo save
	public void save(T obj) throws Exception;
	// Salva os dados mas usando o metodo persist (Mesma coisa do metodo save)
	public void persist(T obj) throws Exception;
	
	// Salva ou atualiza (mesma coisa do merge mas não retorna um object)
	public void saveOrUpdate(T obj) throws Exception;
	
	// Salva os dados e RETORNA O OBJETO (Ele retorna o objeto ja com a Chave primeira)
	public T merge (T obj) throws Exception;
	
	// Realiza o update de dados
	public void update(T obj) throws Exception;
	
	// Realiza Deleta de dados
	public void delete(T obj) throws Exception;
	
	// Carrega a lista de dados de determinada classe
	public List<T> findList(Class<T> obj) throws Exception;
	
	// Retorna um Object passando o id
	public T findById(Class<T> entidade, Long id) throws Exception;
	
	// Retorna uma lista passando a Query para realiza-la
	public List<T> findListByQueryDinamica(String query) throws Exception;
	
	// Construir um proprio update com HQL.
	public void executeUpdateQueryDinamica(String query) throws Exception;
	
	// Construir um proprio update com SQL puro.
	public void executeUpdateSQLDinamico(String query) throws Exception;
	
	// Limpar os objetos da sessão do Hibernate (Caso não saiba qual)
	public void clearSession() throws Exception;
	
	// Retira um objeto especifico da sessão do Hibernate (Caso saiba qual)
	public void evict (T obj) throws Exception;
	
	// Retorna a sessao do Hibernate
	public Session getSession() throws Exception;
	
	// Retorna uma lista de qualquer object passando SQL puro.
	public List<?> getListSQLDinamica(String sql) throws Exception; 
	
	// JDBC  do Spring (Por performace.. em alguns caso usar o JDBC deixa muito mais rapido a consulta)
	public JdbcTemplate getJdbcTemplate();
	// JDBC  do Spring (Por performace.. em alguns caso usar o JDBC deixa muito mais rapido a consulta)
	public SimpleJdbcTemplate getSimpleJdbcTemplate();
	// JDBC  do Spring (Por performace.. em alguns caso usar o JDBC deixa muito mais rapido a consulta)
	public SimpleJdbcInsert getSimpleJdbcInsert();
	
	// Retorna a quantidade de registro em uma tabela.
	public Long totalRegistro(String table) throws Exception;
	
	// Montagem dinamica de sql para consultar registros.
	public Query obterQuery(String query) throws Exception;
	
	// Carregamento por Demanda com JSF e Primefaces (Paginação dos dados).
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
}
