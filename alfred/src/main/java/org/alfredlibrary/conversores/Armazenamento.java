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
 * Conversor de Unidades de Medidas de armazenamento.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
final public class Armazenamento {
	
	public enum Unidade {
		BIT(1), KILOBIT(2), MEGABIT(3), GIGABIT(4), TERABIT(5), PETABIT(6), EXABIT(7), BYTE(8), KILOBYTE(9), MEGABYTE(10), GIGABYTE(11), TERABYTE(12), PETABYTE(13), EXABYTE(14);
		
		private int unidade;
		private Unidade(int unidade) {
			this.unidade = unidade;
		}
		
		@Override
		public String toString() {
			return String.valueOf(unidade);
		}

	}

	/**
	 * 
	 */
	private Armazenamento() {
		throw new AssertionError();
	}

	/**
	 * Converter um valor entre duas grandezas de armazenamento em informática.<br><br>
	 * Exemplo de uso:
	 * <br> 
	 *  // Converter 1 megabyte em bytes.<br>
	 * 	Armazenamento.converter(1, Armazenamento.MEGABYTE, Armazenamento.BYTE);
	 *  
	 * @param valor Valor a ser convertido.
	 * @param unidadeEntrada Tipo do valor de entrada (bytes, kilobytes, megabytes, etc).
	 * @param unidadeSaida Tipo de valor de saída (bytes, kilobytes, megabytes, etc).
	 * @return Valor conertido.
	 */
	public static double converter(double valor, Unidade unidadeEntrada, Unidade unidadeSaida) {
		if ( unidadeSaida.unidade < Unidade.BYTE.unidade && unidadeEntrada.unidade > Unidade.EXABIT.unidade ) {
			for(int i=0; i<(unidadeEntrada.unidade - Unidade.BYTE.unidade);i++)
				valor *= 1024;
			valor *= 8;
			unidadeEntrada = Unidade.BIT;
		}
		if ( unidadeSaida.unidade > Unidade.EXABIT.unidade && unidadeEntrada.unidade < Unidade.BYTE.unidade ) {
			for(int i=0; i<(unidadeEntrada.unidade - Unidade.BIT.unidade);i++)
				valor *= 1024;
			valor /= 8;
			unidadeEntrada = Unidade.BYTE;
		}
		if ( unidadeEntrada.unidade < unidadeSaida.unidade ) {
			for(int i=0; i<(unidadeSaida.unidade - unidadeEntrada.unidade);i++)
				valor /= 1024;
		} else {
			for(int i=0; i<(unidadeEntrada.unidade - unidadeSaida.unidade);i++)
				valor *= 1024;			
		}
		return valor;
	}

}