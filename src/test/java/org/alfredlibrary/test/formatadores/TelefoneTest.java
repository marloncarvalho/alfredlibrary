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
import org.alfredlibrary.formatadores.Telefone;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Formatador de Telefone.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/05/2010
 */
public class TelefoneTest {

	@Test
	public void testFormatacao() {
		String telefone = Telefone.formatar("30115761");
		if ( !"3011-5761".equals(telefone) ) {
			Assert.fail();
		}
		telefone = Telefone.formatar("7130115761");
		if ( !"(71) 3011-5761".equals(telefone) ) {
			Assert.fail();
		}
		telefone = Telefone.formatar("557130115761");
		if ( !"+55 (71) 3011-5761".equals(telefone) ) {
			Assert.fail();
		}
	}

	@Test
	public void testValoresErrados() {
		try {
			Telefone.formatar(null);
			Assert.fail();
		} catch (AlfredException e) {
			
		}
		try {
			Telefone.formatar("123");
			Assert.fail();
		} catch (AlfredException e) {
			
		}
	}

}