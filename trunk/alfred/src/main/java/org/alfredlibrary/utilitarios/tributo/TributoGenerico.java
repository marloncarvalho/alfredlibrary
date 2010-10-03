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
package org.alfredlibrary.utilitarios.tributo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.alfredlibrary.AlfredException;

/**
 * Utilitário que define a forma padrão para cálculo de tributos, baseado nos
 * princípios do Direito Tributário brasileiro. 
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 18/09/2010
 */
public abstract class TributoGenerico {

	/**
	 * Retorna um cálculo simples de tributo a partir de base de cálculo e alíquota
	 * 
	 * @param baseCalculo Valor sobre o qual o tributo será calculado.
	 * @param aliquota Percentual da alíquota a ser aplicada sobre a base de
	 * 					cálculo para se obter o valor do tributo.
	 * @return Valor do tributo.
	 */
	public static double calcular(double baseCalculo, double aliquota) {
		return baseCalculo * (aliquota / 100);
	}
	
	/**
	 * Retorna um cálculo simples de tributo a partir de base de cálculo e alíquota
	 * 
	 * @param baseCalculo Valor sobre o qual o tributo será calculado.
	 * @param aliquota Percentual da alíquota a ser aplicada sobre a base de
	 * 					cálculo na faixa especificada para se obter o valor do tributo.
	 * 					A faixa deve ser passada em ordem crescente e deve ser
	 * 					construída na forma:
	 * 					{valor piso, valor teto, alíquota da faixa}
	 * @param cumulativo Se o tributo for cumulativo, cada alíquota será aplicada dentro dos
	 * 					limites da faixa. Caso contrário, a alíquota é aplicada sobre o valor
	 * 					total da baseCalculo, na faixa em que ela se encontrar. 
	 * @return Valor do tributo.
	 */
	public static double calcular(double baseCalculo, double[][] aliquota, boolean cumulativo) {
		double resultado = 0;
		double ultimoTeto = 0;
		if (aliquota != null) {
			for (int indice = 0; indice < aliquota.length; indice++) {
				if (cumulativo) {
					if (aliquota[indice][0] == 0 && aliquota[indice][1] == 0) {
						resultado += calcular(baseCalculo, aliquota[indice][2]);
					} else if (baseCalculo > aliquota[indice][1] && aliquota[indice][1] != 0D) {
						resultado += calcular(aliquota[indice][1] - (aliquota[indice][0] - 0.01D), aliquota[indice][2]); 
					} else if (baseCalculo >= aliquota[indice][0] && baseCalculo <= aliquota[indice][1]) {
						resultado += calcular(baseCalculo - (aliquota[indice][0] - 0.01D), aliquota[indice][2]);
					} else if (baseCalculo >= aliquota[indice][0] && aliquota[indice][1] == 0D) {
						resultado += calcular(baseCalculo - (aliquota[indice][0] - 0.01D), aliquota[indice][2]);
					}
				} else {
					if (baseCalculo >= aliquota[indice][0] && baseCalculo <= aliquota[indice][1]) {
						return calcular(baseCalculo, aliquota[indice][2]);
					} else if (baseCalculo >= aliquota[indice][0] && aliquota[indice][1] == 0D) {
						return calcular(baseCalculo, aliquota[indice][2]);
					} else if (baseCalculo >= aliquota[indice][1] && aliquota[indice][1] >= ultimoTeto) {
						ultimoTeto = aliquota[indice][1];
						resultado = calcular(baseCalculo, aliquota[indice][2]);
					}
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Retorna um cálculo de tributo a partir de base de cálculo, alíquota e modificadores
	 * desses elementos.
	 * Atenção especial deve ser prestada à forma:
	 * A ordem dos modificadores podem alterar o resultado - deve-se atentar para a
	 * precedência desses modificadores nos atos normativos.
	 * 
	 * @param baseCalculo Valor sobre o qual o tributo será calculado.
	 * @param aliquota Percentual da alíquota a ser aplicada sobre a base de
	 * 					cálculo para se obter o valor do tributo.
	 * @param modificadores Elementos que permitem majorar ou minorar tanto a base
	 * 						de cálculo quanto a alíquota. Devem ser escritos da
	 * 						seguinte forma:
	 * 						1 - "B" (base de cálculo) ou "A" (alíquota)						
	 * 						2 - "+" (majoração) ou "-" (minoração)
	 *                      3 - "%" (percentual) ou "V" (valor)
	 *                      4 - Valor ou percentual de modificação
	 * @return Valor do tributo.
	 */
	public static double calcular(double baseCalculo, double aliquota, String[] modificadores) {
		if (modificadores != null) {
			for (int indice = 0; indice < modificadores.length; indice++) {
				if (modificadores[indice].toUpperCase().charAt(0) == 'B') {
					baseCalculo = modificar(baseCalculo, modificadores[indice]);
				} else if (modificadores[indice].toUpperCase().charAt(0) == 'A') {
					aliquota = modificar(aliquota, modificadores[indice].substring(1));
				}
			}
		}
		return calcular(baseCalculo, aliquota);
	}

	/**
	 * Retorna um cálculo de tributo a partir de base de cálculo, faixas de alíquota
	 * e modificadores desses elementos.
	 * Atenção especial deve ser prestada à forma:
	 * A ordem dos modificadores podem alterar o resultado - deve-se atentar para a
	 * precedência desses modificadores nos atos normativos.
	 * Os modificadores de alíquota são aplicáveis a todas as faixas.
	 * 
	 * @param baseCalculo Valor sobre o qual o tributo será calculado.
	 * @param aliquota Percentual da alíquota a ser aplicada sobre a base de
	 * 					cálculo para se obter o valor do tributo.
	 * @param modificadores Elementos que permitem majorar ou minorar tanto a base
	 * 						de cálculo quanto a alíquota. Devem ser escritos da
	 * 						seguinte forma:
	 * 						1 - "B" (base de cálculo) ou "A" (alíquota)						
	 * 						2 - "+" (majoração) ou "-" (minoração)
	 *                      3 - "%" (percentual) ou "V" (valor)
	 *                      4 - Valor ou percentual de modificação
	 * @param cumulativo Se o tributo for cumulativo, cada alíquota será aplicada dentro dos
	 * 					limites da faixa. Caso contrário, a alíquota é aplicada sobre o valor
	 * 					total da baseCalculo, na faixa em que ela se encontrar.
	 * @return Valor do tributo.
	 */
	public static double calcular(double baseCalculo, double[][] aliquota, String[] modificadores, boolean cumulativo) {
		if (modificadores != null) {
			for (int indice = 0; indice < modificadores.length; indice++) {
				if (modificadores[indice].toUpperCase().charAt(0) == 'B') {
					baseCalculo = modificar(baseCalculo, modificadores[indice]);
				} else if (modificadores[indice].toUpperCase().charAt(0) == 'A') {
					if (aliquota != null) {
						for (int iAliquota = 0; iAliquota < aliquota.length; iAliquota++) {
							aliquota[iAliquota][2] = modificar(aliquota[iAliquota][2], modificadores[indice].substring(1));
						}
					}
				}
			}
		}
		return calcular(baseCalculo, aliquota, cumulativo);
	}
	
	/**
	 * Modifica base de cálculo ou alíquota conforme instruções passadas.
	 * 
	 * @param modificando Base de Cálculo ou Alíquota sujeita à modificação.
	 * @param modificador Elementos que permitem majorar ou minorar tanto a base
	 * 						de cálculo quanto a alíquota. Devem ser escritos da
	 * 						seguinte forma:
	 * 						1 - "+" (majoração) ou "-" (minoração)
	 *                      2 - "%" (percentual) ou "V" (valor)
	 *                      3 - Valor ou percentual de modificação
	 * @return Alíquota ou Base de Cálculo modificada pela instrução passada.
	 */
	private static double modificar(double modificando, String modificador) {
		if (modificador.toUpperCase().charAt(0) == '+') { // Majoração
			if (modificador.toUpperCase().charAt(1) == '%') { // Variação Percentual
				return modificando * (1 + (Double.valueOf(modificador.substring(2)) / 100));
			} else if (modificador.toUpperCase().charAt(1) == 'V') { // Variação de Valor
				return modificando + Double.valueOf(modificador.substring(2));
			}
		} else if (modificador.toUpperCase().charAt(0) == '-') { // Minoração
			if (modificador.toUpperCase().charAt(1) == '%') { // Variação Percentual
				return modificando * (1 - (Double.valueOf(modificador.substring(2)) / 100));
			} else if (modificador.toUpperCase().charAt(1) == 'V') { // Variação de Valor
				return modificando - Double.valueOf(modificador.substring(2));
			}
		} 
		throw new AlfredException("Má formação em modificador!");
	}
	
	/**
	 * Obter e filtrar as alíquotas corretas sobre um determinado tributo conforme a
	 * vigência. Alíquotas sem vigência prevalecem sobre as que não possuem vigência
	 * determinada. Da mesma forma, alíquotas que possuem faixa prevalecem sobre as
	 * que não as definem.
	 * 
	 * @param arquivo .xml de tributos, conforme .dtd publicados em
	 * 					http://code.google.com/p/alfredlibrary
	 * @param dataFatoGerador Data do fato gerador para obtenção das alíquotas
	 * @param incidencia Identificador de incidência para filtragem de alíquotas
	 * @return Lista de alíquotas apenas com dados de faixa e valor
	 */
	public static double[][] obterAliquotas(File arquivo, Date dataFatoGerador, String incidencia) {

		if (dataFatoGerador.compareTo(Calendar.getInstance().getTime()) > 0) {
			dataFatoGerador = Calendar.getInstance().getTime();
		}
		
		// Prepara as alíquotas para retornar apenas as faixas e o valor
		
		Collection<Map<String,Object>> colMapAliquotas = obterMapAliquotas(arquivo, dataFatoGerador, incidencia);
		double[][] resultado = new double[colMapAliquotas.size()][3];
		int indice = 0;
		
		for (Map<String,Object> aliquota : colMapAliquotas) {
			resultado[indice][0] = aliquota.get("piso-faixa") != null ?
					(!aliquota.get("piso-faixa").toString().isEmpty() ?
							Double.valueOf(aliquota.get("piso-faixa").toString()) : 0D)
					: 0D;
			resultado[indice][1] = aliquota.get("teto-faixa") != null ?
					(!aliquota.get("teto-faixa").toString().isEmpty() ?
							Double.valueOf(aliquota.get("teto-faixa").toString()) : 0D)
					: 0D;
			resultado[indice][2] = aliquota.get("valor") != null ?
					(!aliquota.get("valor").toString().isEmpty() ?
							Double.valueOf(aliquota.get("valor").toString()) : 0D)
					: 0D;
			indice++;
		}
		
		return resultado;
	}
	
	/**
	 * Obter e filtrar as alíquotas corretas sobre um determinado tributo conforme a
	 * vigência. Alíquotas sem vigência prevalecem sobre as que não possuem vigência
	 * determinada. Da mesma forma, alíquotas que possuem faixa prevalecem sobre as
	 * que não as definem.
	 * 
	 * @param arquivo .xml de tributos, conforme .dtd publicados em
	 * 					http://code.google.com/p/alfredlibrary
	 * @param dataFatoGerador Data do fato gerador para obtenção das alíquotas
	 * @return Lista de alíquotas com vigência, faixa, valor e incidências
	 */
	public static Collection<Map<String,Object>> obterMapAliquotas(File arquivo, Date dataFatoGerador, String incidencia) {
		
		// Criticar ocorrência de alíquotas com e sem vigência em um mesmo XML.
		Collection<Map<String,Object>> colCandidate1 = new ArrayList<Map<String,Object>>();
		Collection<Map<String,Object>> colCandidate2 = new ArrayList<Map<String,Object>>();
		
		for (Map<String,Object> aliquota : TributoGenericoXMLParser.obterAliquotas(arquivo, dataFatoGerador, incidencia)) {
			if (aliquota.get("inicio-vigencia") == null) {
				colCandidate1.add(aliquota);
			} else {
				colCandidate2.add(aliquota);
			}
		}

		Collection<Map<String,Object>> colFilter1 = new ArrayList<Map<String,Object>>();
		if (!colCandidate1.isEmpty()) {
			colFilter1.addAll(colCandidate1);
		} else {
			colFilter1.addAll(colCandidate2);
		}
		
		// Criticar ocorrência de alíquotas com e sem teto definido em um mesmo XML.
		colCandidate1 = new ArrayList<Map<String,Object>>();
		colCandidate2 = new ArrayList<Map<String,Object>>();
		
		for (Map<String,Object> aliquota : colFilter1) {
			if (aliquota.get("piso-faixa") != null) {
				colCandidate1.add(aliquota);
			} else {
				colCandidate2.add(aliquota);
			}
		}
		
		Collection<Map<String,Object>> colFilter2 = new ArrayList<Map<String,Object>>();
		if (!colCandidate1.isEmpty()) {
			colFilter2.addAll(colCandidate1);
		} else {
			colFilter2.addAll(colCandidate2);
		}
		
		return colFilter2;	
	}
		
}