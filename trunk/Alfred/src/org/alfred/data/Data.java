/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfred.data;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Utilit�rios para Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class Data {

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