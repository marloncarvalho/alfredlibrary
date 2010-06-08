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
package org.alfredlibrary.validadores;
 

import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Validador de PIS/PASEP.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 03/06/2010
 */
final public class PISPASEP {

	private PISPASEP() {
	}

	/**
	 * Verificar se um PIS/PASEP é válido.
	 * 
	 * @param pisPasep PIS/PASEP a ser verificado.
	 * @return Verdadeiro caso seja válido. Falso, caso contrário.
	 */
	public static boolean isValido(String pisPasep) {
		pisPasep = Texto.manterNumeros(pisPasep);
        if (pisPasep.length() != 11)
            return false;
        String numDig = pisPasep.substring(0, 10);
        return org.alfredlibrary.utilitarios.pispasep.PISPASEP.gerarDigitoVerificador(numDig).equals(pisPasep.substring(10, 11));
	}

}