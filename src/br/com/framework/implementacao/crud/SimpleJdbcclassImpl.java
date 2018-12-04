package br.com.framework.implementacao.crud;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
public class SimpleJdbcclassImpl extends SimpleJdbcCall implements Serializable{
	
	public SimpleJdbcclassImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static final long serialVersionUID = 1L;
	

}
