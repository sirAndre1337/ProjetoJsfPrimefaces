package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Responsavel por trazer a data atual
 * v : 17
 * @author Andr� Luis
 *
 */
public class DateUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getDateAtualReportName() {
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		return df.format(Calendar.getInstance().getTime());
	}

	public static String formatDateSql(Date date) {
		StringBuffer retorno = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append("'");
		retorno.append(df.format(date));
		retorno.append("'");
		return retorno.toString();
	}

	public static String formatDateSqlSimple(Date date) {
		StringBuffer retorno = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append(df.format(date));
		return retorno.toString();
	}

}
