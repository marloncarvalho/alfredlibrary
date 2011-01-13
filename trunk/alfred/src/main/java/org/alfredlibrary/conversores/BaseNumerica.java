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
import org.alfredlibrary.validadores.Numeros;


/**
 * Utilitário para conversão entre bases numéricas.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class BaseNumerica {
	
	public enum Base {
		DECIMAL(10), HEXADECIMAL(16), OCTAL(8), BINARIO(2);
		
		private Integer base;
		
		private Base(Integer base) { 
			this.base = base;
		}
		
		@Override
		public String toString() {
			return String.valueOf(base);
		}
		
	}
	
	private BaseNumerica() {
		throw new AssertionError();
	}
	
	/**
	 * Converter um número de uma base para outra.
	 * 
	 * @param valor Valor a ser convertido.
	 * @param baseEntrada A Base Numérica usada como entrada. 
	 * @param baseSaida A Base Numérica desejada na saída.
	 * @return Valor convertido.
	 */
	public static String converter(String valor, Base baseEntrada, Base baseSaida) {
		if ( valor == null || "".equals(valor) ) {
			throw new AlfredException("Nenhum valor informado para conversão.");
		}
		if ( baseEntrada == Base.DECIMAL && !Numeros.isLong(valor) ) {
			throw new AlfredException("Informe um Número Decimal válido!");
		}
		if ( baseEntrada == Base.OCTAL && Long.valueOf(valor) >= 8 ) {
			throw new AlfredException("Informe um Número Octal válido!");
		}
		if ( baseEntrada == Base.BINARIO ) {
			for(int x=0; x < valor.length(); x++) {
				if ( Integer.valueOf(String.valueOf(valor.charAt(x))) > 1 ) {
					throw new AlfredException("Informe um Número Binário válido!");					
				}
			}
		}
		if ( baseEntrada == Base.DECIMAL && baseSaida == Base.BINARIO ) {
			return Long.toBinaryString(Long.valueOf(valor));
		}
		if ( baseEntrada == Base.DECIMAL && baseSaida == Base.OCTAL ) {
			return Long.toOctalString(Long.valueOf(valor));
		}
		if ( baseEntrada == Base.DECIMAL && baseSaida == Base.HEXADECIMAL ) {
			return Long.toHexString(Long.valueOf(valor));
		}
		if ( baseSaida == Base.DECIMAL ) {
			return String.valueOf(Long.parseLong(valor, baseEntrada.base));
		}
		return BaseNumerica.converter(BaseNumerica.converter(valor, baseEntrada, Base.DECIMAL), Base.DECIMAL, baseSaida);
	}
	
}