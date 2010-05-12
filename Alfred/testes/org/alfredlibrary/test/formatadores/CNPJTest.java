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
package org.alfredlibrary.test.formatadores;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.formatadores.CNPJ;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para Formatador de CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/05/2010
 */
public class CNPJTest {

	@Test
	public void testarFormatarCNPJMenos15Numeros() {
		try {
			CNPJ.formatar("15.139.23/0001-84");
			Assert.fail();
		} catch(AlfredException ex) {
		}
	}

	@Test
	public void testarFormatarCNPJCorreto() {
		try {
			String cnpj = CNPJ.formatar("15193923000184");
			if ( cnpj == null || cnpj.length() < 18 ) {
				Assert.fail();
			}
			if ( cnpj.charAt(2) != '.' || cnpj.charAt(6) != '.' ) {
				Assert.fail();
			}
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

}