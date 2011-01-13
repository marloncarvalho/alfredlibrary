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
 * Utilitário para cálculos envolvendo de´´ositos recorrentes
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public final class DepositosRecorrentes {

	public DepositosRecorrentes() {
		throw new AssertionError();
	}

	/**
	 * Obtém o valor futuro com base no valor do depósito periódico, do juro e
	 * do período.
	 * 
	 * @param valor
	 *            Valor do depósito periódico
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor depositado
	 * @param periodos
	 *            Quantidade de períodos no qual os depósitos serão realizados
	 * @param resgateImediato
	 *            Indicativo se o resgate deve ser imediatamente posterior ao
	 *            último depósito ou se deve-se considerar o rendimento do
	 *            montante por mais um período.
	 * @return Valor Acumulado ao longo do período em que os depósitos foram
	 *         realizados
	 */
	public static double obterSaldo(double valor, double juros,
			double periodos, boolean resgateImediato) {
		double acumulado = valor; // Primeiro mês
		for (int indice = 1; indice < periodos; indice++) {
			acumulado = (acumulado * (1 + (juros / 100))) + valor;
		}
		if (!resgateImediato) {
			acumulado *= 1 + (juros / 100);
		}
		return acumulado;
	}

	/**
	 * Obtém o valor do depósito recorrente a ser realizado a fim de obter um
	 * determinado montante ao final de uma certa quantidade de períodos, com
	 * uma certa taxa de juros.
	 * 
	 * @param saldo
	 *            Valor do montante final
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor depositado
	 * @param periodos
	 *            Quantidade de períodos no qual os depósitos serão realizados
	 * @param resgateImediato
	 *            Indicativo se o resgate deve ser imediatamente posterior ao
	 *            último depósito ou se deve-se considerar o rendimento do
	 *            montante por mais um período.
	 * @return Valor do depósito a ser feito periodicamente
	 */
	public static double obterDeposito(double saldo, double juros,
			double periodos, boolean resgateImediato) {
		if (resgateImediato) {
			periodos--;
		}
		double jurosAcumulado = 0;
		for (int indice = 1; indice <= periodos; indice++) {
			jurosAcumulado += Math.pow(1 + (juros / 100), indice);
		}
		return saldo / jurosAcumulado;
	}

	/**
	 * Obtém a quantidade mínima de depósitos periódicos para se acumular um
	 * montante-alvo a uma dada taxa de juros.
	 * 
	 * @param valor
	 *            Valor do depósito periódico
	 * @param saldo
	 *            Valor do montante-alvo
	 * @param juros
	 *            Percentual de juros a que se sujeira o valor depositado
	 * @param resgateImediato
	 *            Indicativo se o resgate deve ser imediatamente posterior ao
	 *            último depósito ou se deve-se considerar o rendimento do
	 *            montante por mais um período.
	 * @return Período mínimo para se atingir o montante-alvo
	 */
	public static int obterPeriodoMinimo(double valor, double saldo,
			double juros, boolean resgateImediato) {
		double acumulado = valor; // Primeiro mês
		int periodoMinimo = 1;
		if (acumulado >= saldo) {
			return periodoMinimo;
		}
		while (acumulado < saldo) {
			if (resgateImediato) {
				acumulado = (acumulado * (1 + (juros / 100))) + valor;
				periodoMinimo++;
			} else {
				acumulado *= 1 + (juros / 100);
				if (acumulado < saldo) {
					acumulado += valor;
					periodoMinimo++;
				}
			}
		}
		return periodoMinimo;
	}

}
