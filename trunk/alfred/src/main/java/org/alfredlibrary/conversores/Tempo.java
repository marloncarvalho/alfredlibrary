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
 * Classe para conversão entre medidas de tempo.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/05/2010
 */
public final class Tempo {

	public enum Unidade {
		HORA(1D), MINUTO(60D), SEGUNDO(3600D), DECIMO_SEGUNDO(36000D),
		CENTESIMO_SEGUNDO(360000D), MILESIMO_SEGUNDO(3600000D);
		
		private Double unidade;
		
		private Unidade(Double unidade) {
			this.unidade = unidade;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.unidade);
		}
	}

	private Tempo() {
		throw new AssertionError();
	}
	
	/**
	 * Converter uma unidade de volume para outra.
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
