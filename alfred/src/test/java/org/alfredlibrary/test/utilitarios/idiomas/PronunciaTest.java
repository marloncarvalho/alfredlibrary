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
package org.alfredlibrary.test.utilitarios.idiomas;

import java.io.InputStream;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.idiomas.Pronuncia;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário Pronuncia.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class PronunciaTest {

	@Test
	public void testarOuvirPronuncia() {
		try {
			Pronuncia.ouvirPronuncia(Pronuncia.INGLES, "love");
		} catch (AlfredException exc) {
			Assert.fail();
		}
	}

	@Test
	public void testarObterPronuncia() {
		try {
			InputStream is = Pronuncia.obterPronuncia(Pronuncia.INGLES, "love");
			Assert.assertNotNull(is);
		} catch (AlfredException exc) {
			Assert.fail();
		}
	}

}