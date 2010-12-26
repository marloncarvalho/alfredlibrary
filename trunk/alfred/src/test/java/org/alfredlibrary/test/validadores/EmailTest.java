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

import org.alfredlibrary.validadores.Email;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Validador de E-mail.
 * 
 * @author Marlon Silva Carvalho
 * @since 15/05/2010
 */
public class EmailTest {

	@Test
	public void testEmailInvalido() {
		Assert.assertFalse(Email.isValido("marlon=@t.com"));
		Assert.assertFalse(Email.isValido("marlon"));
		Assert.assertFalse(Email.isValido("marlon.carvalhogmail.com"));
		Assert.assertFalse(Email.isValido("marlon@g@g.com"));
		Assert.assertFalse(Email.isValido("marlong@g....com"));
		Assert.assertFalse(Email.isValido("marlon@.com"));
		Assert.assertFalse(Email.isValido("marlon@ccom"));
		Assert.assertFalse(Email.isValido("asf@1.com"));
	}
	
	@Test
	public void testEmailValido() {
		try {
			Assert.assertTrue(Email.isValido("marlon.carvalho@gmail.com"));
			Assert.assertTrue(Email.isValido("alonso@ferrari.com"));
			Assert.assertTrue(Email.isValido("marlon@silvacarvalho.net"));
			Assert.assertTrue(Email.isValido("alfred@alfredlibrary.org"));
			Assert.assertTrue(Email.isValido("alfred@click21.com.br"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}