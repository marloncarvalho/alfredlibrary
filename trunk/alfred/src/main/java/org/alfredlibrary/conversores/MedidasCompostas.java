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

package org.alfredlibrary.conversores;

import java.util.List;

import org.alfredlibrary.AlfredException;

/**
 * Classe parâmetro para conversão de medidas compostas simétricas.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/05/2010
 */
public final class MedidasCompostas {

	/**
	 * Converter um valor de uma unidade composta para outra.
	 * As medidas de entrada e saída devem possuir a mesma composição.
	 * 
	 * @param valor Valor que será convertido.
	 * @param unidadeEntrada Em que tipo de representação o valor está representado.
	 * @param unidadeSaida Em que tipo de representação o valor de saída será representado.
	 * @return Valor convertido.
	 */
	public static double converter (double valor, List<UnidadeComposta> unidadeEntrada,
			List<UnidadeComposta> unidadeSaida) {
		// Verifica se as unidades de entrada e saída possuem o mesmo número de componentes
		if (unidadeEntrada == null || unidadeSaida == null) {
			throw new AlfredException("Unidade de entrada ou saída não informada!");
		} else if (unidadeEntrada.size() != unidadeSaida.size()) {
			throw new AlfredException("Unidade de entrada e saída assimétricas!");
		}
		double resultado = valor;
		for (int i = 0; i < unidadeEntrada.size(); i++) {
			// Verifica se a unidade componente da entrada é compatível com a da saída
			if (unidadeEntrada.get(i).isNumerador() != unidadeSaida.get(i).isNumerador() ||
					unidadeEntrada.get(i).getPotencia() != unidadeSaida.get(i).getPotencia()) {
				throw new AlfredException("Unidades de entrada e saída informadas em ordens diferentes!");
			} else {
				// Aplica a conversão de cada unidade ao valor passado
				if (unidadeEntrada.get(i).isNumerador()) {
					resultado *= Math.pow(converter(1, unidadeEntrada.get(i).getUnidade(), unidadeSaida.get(i).getUnidade()), unidadeEntrada.get(i).getPotencia());
				} else {
					resultado /= Math.pow(converter(1, unidadeEntrada.get(i).getUnidade(), unidadeSaida.get(i).getUnidade()), unidadeEntrada.get(i).getPotencia());
				}
			}
		}
		return resultado;
	}

	/**
	 * Passa a responsabilidade aos conversores individuais para fazer a conversão de
	 * cada medida.
	 * 
	 * @param valor Valor que será convertido (para o propósito dessa classe, sempre 1).
	 * @param unidadeEntrada Em que tipo de representação o valor está representado.
	 * @param unidadeSaida Em que tipo de representação o valor de saída será representado.
	 * @return Valor convertido.
	 */
	private static double converter(int valor, Object unidadeEntrada, Object unidadeSaida) {
		if (unidadeEntrada instanceof Armazenamento.Unidade && unidadeSaida instanceof Armazenamento.Unidade) {
			return Armazenamento.converter(valor, (Armazenamento.Unidade)unidadeEntrada, (Armazenamento.Unidade)unidadeSaida);
		} else if (unidadeEntrada instanceof Comprimento.Unidade && unidadeSaida instanceof Comprimento.Unidade) {
			return Comprimento.converter(valor, (Comprimento.Unidade)unidadeEntrada, (Comprimento.Unidade)unidadeSaida);
		} else if (unidadeEntrada instanceof Massa.Unidade && unidadeSaida instanceof Massa.Unidade) {
			return Massa.converter(valor, (Massa.Unidade)unidadeEntrada, (Massa.Unidade)unidadeSaida);
		} else if (unidadeEntrada instanceof Temperatura.Unidade && unidadeSaida instanceof Temperatura.Unidade) {
			return Temperatura.converter(valor, (Temperatura.Unidade)unidadeEntrada, (Temperatura.Unidade)unidadeSaida);
		} else if (unidadeEntrada instanceof Volume.Unidade && unidadeSaida instanceof Volume.Unidade) {
			return Volume.converter(valor, (Volume.Unidade)unidadeEntrada, (Volume.Unidade)unidadeSaida);
		} else if (unidadeEntrada instanceof Tempo.Unidade && unidadeSaida instanceof Tempo.Unidade) {
			return Tempo.converter(valor, (Tempo.Unidade)unidadeEntrada, (Tempo.Unidade)unidadeSaida);
		} else {
			throw new AlfredException("A conversão entre as medidas não pode ser realizada! Tipos incompatíveis");
		}
	}
	
	private MedidasCompostas() {
		throw new AssertionError();
	}
	
}
