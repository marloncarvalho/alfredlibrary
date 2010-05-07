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
 * @since 07/06/2009
 */
final public class Massa {
	
	public enum Unidade {
		TONELADA(1D), QUILOGRAMA(1000D), HECTOGRAMA(10000D), GRAMA(1000000D), CENTIGRAMA(100000000D), 
		QUILATE(5000000D), MILIGRAMA(1000000000D), MICROGRAMA(1000000000000D), NANOGRAMA(1000000000000000D);
		
		private Double unidade;
		private Unidade(Double unidade) {
			this.unidade = unidade;
		}
		@Override
		public String toString() {
			return String.valueOf(unidade);
		}
	}

	private Massa() {}

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