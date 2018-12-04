package br.com.framework.implementacao.crud;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Usar o JDBC do spring caso as consultas com o hibernate estejam gargalando.
 * @author Andr� Luis
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JdbcTemplateImpl extends JdbcTemplate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public JdbcTemplateImpl(DataSource dataSource) {
		super(dataSource);
	}

}
