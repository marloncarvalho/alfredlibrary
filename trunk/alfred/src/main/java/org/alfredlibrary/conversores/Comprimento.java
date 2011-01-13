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
 * @author Marlon Silva Carvalho
 * @author Rodrigo Moreira Fagundes
 * @since 03/06/2009
 */
final public class Comprimento {

	public enum Unidade {
		KM(1D), HECTOMETRO(10D), DECAMETRO(100D), METRO(1000D), DECIMETRO(10000D),
		CENTIMETRO(100000D), MILIMETRO(1000000D), MICROMETRO(1000000000D),
		NANOMETRO(1000000000000D), ANGSTROM(10000000000000D),
		POLEGADA(39370.07874015748031496062992126D),
		JARDA(1093.6132983377077865266841644794D),
		PE(3280.8398950131233595800524934383),
		PE_US(3280.8333290409097278381431060784D),
		MILHA_TERRESTRE(0.67590402162892869212571814802298D),
		MILHA_NAUTICA(0.53995680345572354211663066954644D),
		MILHA_IMPERIAL(0.53961182483768476308882442326288D),
		LEGUA_TERRESTRE_ANTIGA(0.15151515151515151515151515151515D),
		LEGUA_TERRESTRE(0.20712373074577798987247806145444D),
		LEGUA_MARITIMA_18_GRAU(0.16201153522130775711230639621541D),
		LEGUA_MARITIMA(0.17999985600011519990784007372794D),
		LEGUA_MARITIMA_25_GRAU(0.22500022500022500022500022500023D);
		
		private Double unidade;
		
		private Unidade(Double unidade) {
			this.unidade = unidade;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.unidade);
		}
	}

	private Comprimento() {
		throw new AssertionError();
	}
	
	/**
	 * Converter uma unidade de comprimento para outra.
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