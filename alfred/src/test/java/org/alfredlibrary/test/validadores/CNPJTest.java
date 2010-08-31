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
package org.alfredlibrary.test.validadores;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.validadores.CNPJ;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Validador de CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 15/04/2010
 */
public class CNPJTest {

	@Test
	public void testValidarCerto() {
		try {
			if ( !CNPJ.isValido("04.593.860/0001-37") ) {
				Assert.fail();
			}
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

	@Test
	public void testarValidarCNPJInvalido() {
		try {
			if ( CNPJ.isValido("00.000.000/0001-92") ) {
				Assert.fail();
			}
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}
}