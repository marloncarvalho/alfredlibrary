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
package org.alfredlibrary.utilitarios.trabalhista;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.alfredlibrary.utilitarios.tributo.FGTS.FGTS;
import org.alfredlibrary.utilitarios.tributo.INSS.INSS;
import org.alfredlibrary.utilitarios.tributo.IR.IRRF;

/**
 * Utilitário que permite fazer cálculos salariais. 
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 19/09/2010
 */
public final class CalculoSalarial {
	
	private CalculoSalarial() {
		throw new AssertionError();
	}
	
	/**
	 * Retorna o salário líquido a partir do valor bruto e das
	 * contribuições padrão de impostos.
	 * 
	 * @param mapaTributario Arquivos com alíquotas tributárias e indicação de incidência.
	 * 						Deve ser construído com:
	 * 						chave (String) = referência do tributo (ex: "IRRF")
	 * 						valor (Object[]) = construído na forma { File , String }, onde
	 * 						o File é o arquivo com as alíquotas e a String é o indicativo
	 * 						da incidência para ajuste de alíquota daquele tributo.
	 * @param salarioBruto Salário bruto percebido pelo funcionário.
	 * @param dataRecebimento Data de recebimento do salário, para
	 * 							determinação das alíquotas tributárias
	 * 							pertinentes.
	 * @return Valor líquido do salário a ser percebido pelo funcionário,
	 * 			após descontos de contribuições tributárias.
	 */
	public static double calcular(Map<String,Object[]> mapaTributario, double salarioBruto,
			Date dataRecebimento) {
		double liquido = salarioBruto;
		
		liquido -= INSS.calcular(liquido, INSS.obterAliquotas(
				(File)((Object[])mapaTributario.get("INSS"))[0], dataRecebimento,
				((Object[])mapaTributario.get("INSS"))[1].toString()), false);
		liquido -= IRRF.calcular(liquido, IRRF.obterAliquotas(
				(File)((Object[])mapaTributario.get("IRRF"))[0], dataRecebimento,
				((Object[])mapaTributario.get("IRRF"))[1] != null ?
						((Object[])mapaTributario.get("IRRF"))[1].toString() :
						null
				), true);
		
		return liquido;
	}
	
	/**
	 * Retorna o valor do FGTS a ser recolhido em favor do funcionário,
	 * em função do seu salário.
	 * 
	 * @param aliquota Arquivo com alíquotas de recolhimento ao FGTS.
	 * @param salarioBruto Salário bruto percebido pelo funcionário.
	 * @param dataRecebimento Data de recebimento do salário, para
	 * 							determinação das alíquotas tributárias
	 * 							pertinentes.
	 * @param incidencia Definição da incidência do tributo para localizar
	 * 					a alíquota adequada.
	 * @return Valor do FGTS a ser recolhido.
	 */
	public static double calcularFGTS(File aliquota, double salarioBruto,
			Date dataRecebimento, String incidencia) {
		return FGTS.calcular(salarioBruto, INSS.obterAliquotas(
				aliquota, dataRecebimento, incidencia), false);
	}
	
	/**
	 * Retorna o valor da multa rescisória do FGTS a ser paga ao
	 * funcionário nos casos previstos na legislação, em função
	 * do seu salário.
	 * 
	 * @param aliquota Arquivo com alíquotas de recolhimento ao FGTS.
	 * @param salarioBruto Salário bruto percebido pelo funcionário.
	 * @param dataRecebimento Data de recebimento do salário, para
	 * 							determinação das alíquotas tributárias
	 * 							pertinentes.
	 * @param incidencia Definição da incidência do tributo para localizar
	 * 					a alíquota adequada.
	 * @return Valor do FGTS a ser recolhido.
	 */
	public static double calcularFGTSRescisao(File aliquota, double salarioBruto,
			Date dataRecebimento, String incidencia) {
		return FGTS.calcular(salarioBruto, INSS.obterAliquotas(
				aliquota, dataRecebimento, incidencia), false);
	}

}
