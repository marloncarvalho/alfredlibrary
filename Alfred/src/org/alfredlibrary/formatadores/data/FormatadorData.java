package org.alfredlibrary.formatadores.data;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Utilitário para fazer conversões com Date.
 * 
 * @author Rodrigo Moreira Fagundes
 * @author Marlon Silva Carvalho
 * @since 14/04/2010
 */
public final class FormatadorData {

	/**
	 * Obtém uma String correspondente a uma determinada data,
	 * de acordo com o formato especificado.
	 * 
	 * @param data Data.
	 * @param formato Formato do texto de saída.
	 * @return String Texto com a data no formato especificado.
	 */
	public static String obterTextoDeData (Date data, String formato) {
		if (data != null) {
			DateFormat dateFormat = new SimpleDateFormat(formato);
			return dateFormat.format(data);
		} else {
			return "";
		}
	}
	
	/**
	 * Obtém uma Data a partir de um texto passado no formato especificado.
	 * 
	 * @param strData Texto contendo a data.
	 * @param formato Formato do texto passado em "strData".
	 * @return Date Data.
	 */
	public static Date obterDataDeTexto (String strData, String formato) throws ParseException {
		if (strData != null) {
			DateFormat df = new SimpleDateFormat(formato);
			return (Date)df.parse(strData);
		}
		return null;
	}
	
	/**
	 * Formatar uma data no formato dd de MM de aaaa. 
	 * Exemplo: 21 de Janeiro de 2009
	 * 
	 * @param data Data a ser formatada.
	 * @return Data formatada.
	 */
	public static String formatarDataPorExtenso(Date data) {
		Calendar cal = null;
		cal = new GregorianCalendar();
		cal.setTime(data);
		String mes = new DateFormatSymbols(Locale.getDefault()).getMonths()[cal.get(Calendar.MONTH)];
		String dia = (cal.get(Calendar.DAY_OF_MONTH) < 10)  ?  "0" + cal.get(Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		return new StringBuilder().append(dia).append(" de ").append(mes).append(" de ").append(cal.get(Calendar.YEAR)).toString();
	}

}