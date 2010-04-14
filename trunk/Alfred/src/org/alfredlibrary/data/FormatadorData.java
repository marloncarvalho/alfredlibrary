package org.alfredlibrary.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilitário para fazer conversões com Date.
 * 
 * @author Rodrigo Moreira Fagundes
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
}
