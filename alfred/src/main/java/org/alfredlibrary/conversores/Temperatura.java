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

import org.alfredlibrary.AlfredException;

/**
 * Classe utilitária para conversão de temperaturas.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Temperatura {
	
	public enum Unidade {
		CELSIUS(1), FAHRENHEIT(2), KELVIN(3);
		
		private int unidade;
		private Unidade(int unidade) {
			this.unidade = unidade;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.unidade);
		}

	}

	private Temperatura() {
		throw new AssertionError();
	}

	/**
	 * Converter um valor de temperatura para outro.
	 * Código "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param valor Valor que será convertido.
	 * @param entrada Em que tipo de representação o valor está representado.
	 * @param saida Em que tipo de representação o valor de saída será representado.
	 * @return Valor convertido.
	 */
	public static float converter(float valor, Unidade entrada, Unidade saida) {
		if ( entrada == Unidade.CELSIUS && saida == Unidade.FAHRENHEIT )
			return valor * 9 / 5 + 32;
		if ( entrada == Unidade.CELSIUS && saida == Unidade.KELVIN )
			return valor + 273.15f;
		if ( entrada == Unidade.FAHRENHEIT && saida == Unidade.CELSIUS )
			return (valor - 32) * 5 / 9;
		if ( entrada == Unidade.FAHRENHEIT && saida == Unidade.KELVIN )
			return converter((converter(valor, Unidade.FAHRENHEIT, Unidade.CELSIUS)), Unidade.CELSIUS, Unidade.KELVIN);
		if ( entrada == Unidade.KELVIN && saida == Unidade.CELSIUS )
			return valor - 273.15f;
		if ( entrada == Unidade.KELVIN && saida == Unidade.FAHRENHEIT )
			return converter((converter(valor, Unidade.KELVIN, Unidade.CELSIUS)), Unidade.CELSIUS, Unidade.FAHRENHEIT);
		throw new AlfredException("Não foi possível realizar a conversão de temperatura solicitada.");
	}

}