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
package org.alfredlibrary.utilitarios.pispasep;

import org.alfredlibrary.utilitarios.digitoverificador.Modulo11;

/**
 * Utilitários de PIS/PASEP.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
final public class PISPASEP {

	private PISPASEP() {
		throw new AssertionError();
	}
	
	/**
     * Gerar um PIS/PASEP arbitrário.
	 * Todos os direitos são do autor do código.
	 *  
     * @return PIS/PASEP gerado arbitrariamente.
     */
    public static String gerar() {
        StringBuilder iniciais = new StringBuilder();
        Integer numero;
        for (int i = 0; i < 10; i++) {
            numero = Integer.valueOf((int) (Math.random() * 10));
            iniciais.append(numero.toString());
        }
        return iniciais.toString() + gerarDigitoVerificador(iniciais.toString());
    }
    
	/**
	 * Método que calcula o dígito verificador, observando se está correto.
	 * 
	 * @param num
	 * @return Dígito verificador.
	 */
	public static String gerarDigitoVerificador(String num) {
		return Modulo11.obterDVBase10(num, false);
	}
}
