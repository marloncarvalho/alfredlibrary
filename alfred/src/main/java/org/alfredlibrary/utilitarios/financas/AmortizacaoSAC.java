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
package org.alfredlibrary.utilitarios.financas;

/**
 * Utilitário para cálculos de prestações para financiamentos com amortização pelo SAC.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public final class AmortizacaoSAC {
	
	private AmortizacaoSAC() {
		throw new AssertionError();
	}

	/**
	 * Obtém o acompanhamento do financiamento, prestação a prestação, com informações
	 * do saldo devedor e valor amortizado e juros da parcela.
	 * 
	 * @param valorFinanciado Valor do financiamento, que é o saldo devedor inicial
	 * @param juros Percentual de juros a que se sujeira o valor presente, pela periodicidade
	 * 				do pagamento de parcelas. Se a parcela for mensal, deve-se registrar os
	 * 				juros a.m. 
	 * @param periodos Quantidade de parcelas do financiamento
	 * @param jurosSimples Indicativo se o método de cálculo de juros é simples (TRUE) ou
	 * 						composto (FALSE)
	 * @return Tabela representativa do financiamento
	 */
	public static double[][] obter (double valorFinanciado, double juros, int periodos, boolean jurosSimples) {
		double amortizacao = valorFinanciado / periodos;
		double[][] resultado = new double[periodos + 1][4];
		double jurosPrestacao = 0D;
		resultado[0] = new double[] {0, 0, 0, valorFinanciado};
		for (int indice = 1; indice <= periodos; indice++) {
			if (jurosSimples) {
				jurosPrestacao = JurosSimples.obterValorFuturo(resultado[indice-1][3], juros, 1) - resultado[indice-1][3];
			} else {
				jurosPrestacao = JurosCompostos.obterValorFuturo(resultado[indice-1][3], juros, 1) - resultado[indice-1][3];
			}
			double prestacao = amortizacao + jurosPrestacao;
			double saldoDevedor = resultado[indice-1][3] - amortizacao;
			resultado[indice] = new double[] {prestacao, jurosPrestacao, amortizacao, saldoDevedor};
		}
		return resultado;
	}
	
}
