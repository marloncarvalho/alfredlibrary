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
 * Classe para conversão entre medidas de comprimento.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 11/05/2010
 */
public final class Volume {

	public enum Unidade {
		QUILOLITRO(1D), HECTOLITRO(10D), DECALITRO(100D), LITRO(1000D), DECILITRO(10000D),
		CENTILITRO(100000D), MILILITRO(1000000D), MICROLITRO(1000000000D),
		NANOLITRO(1000000000000D),
		ONCA_UK(35195.07972785404600436858927122D),
		ONCA_US(33814.022701842997168627189965973D),
		GALAO_LIQUIDO(264.17205235814841537989992160916D),
		GALAO_SECO(227.02074606721397290857369961855D),
		GALAO_IMPERIAL(219.96924829908778752730368294512D),
		BARRIL_PETROLEO_US(6.2898107704321051280928552764086D),
		BARRIL_PETROLEO_UK(6.2848356656882225007801052270036D),
		BARRIL_CERVEJA(8.521679108327368238061287793844D);
		
		private Double unidade;
		
		private Unidade(Double unidade) {
			this.unidade = unidade;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.unidade);
		}
	}

	private Volume() {
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
