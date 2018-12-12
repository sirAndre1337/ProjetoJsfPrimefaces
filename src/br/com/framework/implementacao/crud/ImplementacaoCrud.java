package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.framework.session.HibernateUtil;
import br.com.project.model.classes.Entidade;
/**
 * v : 5,6.
 * @author André Luis
 *
 * @param <T>
 */
@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T>{

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory  sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImplents simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcclassImpl simpleJdbcclass;
	
	public SimpleJdbcclassImpl getSimpleJdbcclass() {
		return simpleJdbcclass;
	}

	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		
		StringBuilder query = new StringBuilder();
		query.append("select distinct(entity) from ")
		.append(entidade.getSimpleName())
		.append(" entity ");
		
		List<T> list = new ArrayList<T>();
		
		list = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return list;
	}

	@Override
	public T findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		
		T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public List<T> findListByQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).list();
		
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDinamico(String query) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from ").append(table);
		
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}
	
	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no parametro -> iniciaNoRegistro e obtendo maximo de
	 * resultados passado em -> maximoResultado
	 * 
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return List<T>
	 * @throws Exception
	 */
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(iniciaNoRegistro).setMaxResults(maximoResultado).list(); // Ex select from Cidade que comece no registro 50 e termine no registro 80, trazendo 30 registro do BD
		
		return lista;
	}
	
	// Verifica se existe a Session Factory
	private void validaSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		validaTransaction();
	}
	
	// Verifica se existe Transação no sessionFactory
	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	// Commita as operações com o banco de dados usando Ajax
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	// RollBack nas operações com o banco de dados usando Ajax
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	// Roda instantaneamente o SQL no banco de dados (ele roda no banco, mas não faz o commit , pq caso haja uma requisiçao, e um objeto necessite de outro, o 1 obj ja estara no BD)
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
	//Retorna um lista de array de objetos.
		public List<Object[]> getListSqlDinamicaArray(String sql) throws Exception {
			validaSessionFactory();
			List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
			return lista;
		}
		
		public T findUniqueByQueryDinamica(String query) throws Exception {
			validaSessionFactory();
			T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
			return obj;
		}

		public T findUninqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
			validaSessionFactory();
			StringBuilder query = new StringBuilder();
			query.append(" select entity from ").append(entidade.getSimpleName()).append(" entity where entity.").append(atributo)
			.append(" = '").append(valor).append("' ").append(condicao);
			
			T obj = (T) this.findUniqueByQueryDinamica(query.toString());
			
			return obj;
		}
	
}
