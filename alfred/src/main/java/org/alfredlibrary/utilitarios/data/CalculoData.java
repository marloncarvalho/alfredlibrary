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
package org.alfredlibrary.utilitarios.data;

import java.util.Calendar;
import java.util.Date;

import org.alfredlibrary.AlfredException;

/**
 * Utilitário para realizar cálculos com Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class CalculoData {

	private CalculoData() {
		throw new AssertionError();
	}
	/**
	 * Somar uma determinada quantidade de dias a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeDias Quantidade de dias a somar.
	 * @return Data com a soma de dias.
	 */
	public static Date somarDias(Date data, int quantidadeDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, quantidadeDias);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de dias a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeDias Quantidade de dias a subtrair.
	 * @return Data com a subtração de dias.
	 */
	public static Date subtrairDias(Date data, int quantidadeDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, -quantidadeDias);
		return calendar.getTime();		
	}

	/**
	 * Somar uma determinada quantidade de meses a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeMeses Quantidade de meses a somar.
	 * @return Data com a soma de meses.
	 */
	public static Date somarMeses(Date data, int quantidadeMeses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, quantidadeMeses);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de meses a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeMeses Quantidade de meses a subtrair.
	 * @return Data com a subtração de meses.
	 */
	public static Date subtrairMeses(Date data, int quantidadeMeses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, -quantidadeMeses);
		return calendar.getTime();
	}
	
	/**
	 * Somar uma determinada quantidade de anos a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeAnos Quantidade de anos a somar.
	 * @return Data com a soma de anos.
	 */
	public static Date somarAnos(Date data, int quantidadeAnos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, quantidadeAnos);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de anos a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeAnos Quantidade de anos a subtrair.
	 * @return Data com a subtração de anos.
	 */
	public static Date subtrairAnos(Date data, int quantidadeAnos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, -quantidadeAnos);
		return calendar.getTime();
	}

	/**
	 * Calcular a quantidade de dias que existe entre as duas datas informadas.
	 * 
	 * @param data1 Data 1.
	 * @param data2 Data 2.
	 * @return Diferença em dias.
	 */
	public static int calcularDiferencaDias(Date data1, Date data2) {
		Calendar calendarData1 = Calendar.getInstance();
		calendarData1.setTime(data1);
		Long dateStamp1 = (calendarData1.getTimeInMillis() - (calendarData1.getTimeInMillis() % (1000*60*60*24))) / (1000*60*60*24);
		Calendar calendarData2 = Calendar.getInstance();
		calendarData2.setTime(data2);
		Long dateStamp2 = (calendarData2.getTimeInMillis() - (calendarData2.getTimeInMillis() % (1000*60*60*24))) / (1000*60*60*24);
		Long diff = dateStamp1 - dateStamp2;
		return Math.abs(diff.intValue());
	}
	
	/**
	 * Calcular a quantidade de dias que existe entre as duas datas informadas.
	 * 
	 * @author Rodrigo Moreira Fagundes
	 * @since 12/04/2010
	 * 
	 * @param data1 Data Maior.
	 * @param data2 Data Menor.
	 * @param isInclusiva Se a data inicial é ou não incluída no cálculo
	 * @return Diferença em dias.
	 * 
	 */
	public static int calcularDiferencaDias(Date data1, Date data2, boolean isInclusiva) {
		if (isInclusiva) {
			return (calcularDiferencaDias(data1, data2) + 1);
		} else {
			return calcularDiferencaDias(data1, data2);
		}
	}
	
	/**
	 * Calcular qual a interseção entre dois períodos
	 * 
	 * @param data1 Período para comparação - array de tamanho 2
	 * @param data2 Período para comparação - array de tamanho 2
	 * @return Período da interseção
	 */
	public static Date[] calcularIntersecao(Date[] data1, Date[] data2) {
		if (data1.length != 2 || data2.length != 2) {
			throw new AlfredException("Arrays devem possuir tamanho dois em ambos os parâmetros!");
		}
		
		Date dataInicio1, dataTermino1, dataInicio2, dataTermino2;  
		if (data1[0].compareTo(data1[1]) <= 0) {
			dataInicio1 = data1[0];
			dataTermino1 = data1[1];
		} else {
			dataInicio1 = data1[1];
			dataTermino1 = data1[0];
		}
		if (data2[0].compareTo(data2[1]) <= 0) {
			dataInicio2 = data2[0];
			dataTermino2 = data2[1];
		} else {
			dataInicio2 = data2[1];
			dataTermino2 = data2[0];
		}
		
		Date[] dataIntersecao = new Date[2];
		if (dataTermino1.compareTo(dataInicio2) < 0 || dataInicio1.compareTo(dataTermino2) > 0) {
			return null;
		} else {
			dataIntersecao[0] = (dataInicio1.compareTo(dataInicio2) >= 0 ? dataInicio1 : dataInicio2);
			dataIntersecao[1] = (dataTermino1.compareTo(dataTermino2) <= 0 ? dataTermino1 : dataTermino2);
			return dataIntersecao;
		}
	}
	
	/**
	 * Verifica se um período está compreendido pelo outro
	 * 
	 * @param data1 Período para comparação - array de tamanho 2
	 * @param data2 Período para comparação - array de tamanho 2
	 * @return Indicador de compreensão:
	 * 			-1 se data1 contiver data2
	 * 			 0 se os períodos não tiverem interseção ou se a interseção não coincidir com um deles
	 * 			 1 se data2 contiver data1
	 */
	public static int calcularCompreensao(Date[] data1, Date[] data2) {
		if (data1.length != 2 || data2.length != 2) {
			throw new AlfredException("Arrays devem possuir tamanho dois em ambos os parâmetros!");
		}
		
		Date dataInicio1, dataTermino1, dataInicio2, dataTermino2;  
		if (data1[0].compareTo(data1[1]) <= 0) {
			dataInicio1 = data1[0];
			dataTermino1 = data1[1];
		} else {
			dataInicio1 = data1[1];
			dataTermino1 = data1[0];
		}
		if (data2[0].compareTo(data2[1]) <= 0) {
			dataInicio2 = data2[0];
			dataTermino2 = data2[1];
		} else {
			dataInicio2 = data2[1];
			dataTermino2 = data2[0];
		}
		
		Date[] dataIntersecao = calcularIntersecao(data1, data2);
		if (dataIntersecao != null) {
			if (dataInicio1.compareTo(dataIntersecao[0]) == 0 && dataTermino1.compareTo(dataIntersecao[1]) == 0) {
				return -1;
			} else if (dataInicio2.compareTo(dataIntersecao[0]) == 0 && dataTermino2.compareTo(dataIntersecao[1]) == 0) {
				return 1;
			}
		}
		return 0;
	}

	public static Date criarDataComPrimeiroDiaDoMes(int mes, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date criarDataComUltimaDiaDoMes(int mes, int ano) {
		if ( mes == Calendar.DECEMBER) {
			mes = Calendar.JANUARY;
		} else {
			mes++;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return subtrairDias(calendar.getTime(), 1);
	}
	
}