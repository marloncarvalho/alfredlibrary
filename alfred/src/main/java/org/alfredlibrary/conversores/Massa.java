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

/**
 * Classe para conversão entre medidas de massa.
 * 
 * @author Marlon Silva Carvalho
 * @author Rodrigo Moreira Fagundes
 * @since 07/06/2009
 */
final public class Massa {
	
	public enum Unidade {
		TONELADA(1D), QUILOGRAMA(1000D), HECTOGRAMA(10000D), GRAMA(1000000D), CENTIGRAMA(100000000D), 
		QUILATE(5000000D), MILIGRAMA(1000000000D), MICROGRAMA(1000000000000D), NANOGRAMA(1000000000000000D),
		ONCA_TROY(32150.7453282234D), ONCA_AVORDUPIOS(35273.9619495804D), LIBRA_TROY(2679.228880719D),
		LIBRA_AVORDUPOIS(2204.62262184878D), LIBRA_IMPERIAL(2204.62277738034D), ARROBA(35.2739619495804D),
		ARROBA_PT_BR(68.0781537204711D), ARROBA_PT_BR_APROX(66.6666666666667D), ARROBA_SPA(61.2395172735771D);
		
		/* ONCA_TROY = 31.103478g - para metais preciosos, gemas ou medicamentos
		 * ONCA_AVORDUPIOS = 28.349523125 g - para objetos em geral
		 * LIBRA_TROY = 373.2417216 g - para metais preciosos, gemas ou medicamentos
		 * LIBRA_AVORDUPOIS = 453.592370 g - para objetos em geral
		 * LIBRA_IMPERIAL = 453.592338 g - definição obsoleta do Ato de Pesos e Medidas de 1878 (UK)
		 * ARROBA = 28349.523125 g - para a conversão original
		 * ARROBA_PT_BR = 14689 g - para padrão brasileiro (gado) e português (cereais)
		 * ARROBA_PT_BR_APROX = 15000 g - para padrão brasileiro (gado) e português (cereais) aproximado
		 * ARROBA_SPA = 16329.32532 g - para padrão da região de Aragão, na Espanha
		 */ 
		
		private Double unidade;
		private Unidade(Double unidade) {
			this.unidade = unidade;
		}
		@Override
		public String toString() {
			return String.valueOf(unidade);
		}
	}

	private Massa() {
		throw new AssertionError();
	}

	/**
	 * Converter uma unidade de massa para outra.
	 * 
	 * @param valor Valor a ser convertido.
	 * @param unidadeEntrada Unidade de Entrada.
	 * @param unidadeSaida Unidade de Saída.
	 * @return Valor convertido.
	 */
	public static double converter(double valor, Unidade unidadeEntrada, Unidade unidadeSaida) {
		if ( unidadeEntrada.unidade > unidadeSaida.unidade )
			return (valor/(unidadeEntrada.unidade/unidadeSaida.unidade));
		else return (valor*(unidadeSaida.unidade/unidadeEntrada.unidade));
	}
	
}