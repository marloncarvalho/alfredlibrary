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

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.idiomas.Babelfish;
import org.junit.Test;

/**
 * Teste do utilitário Babelfish.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class BabelfishTest {

	@Test
	public void testarTraducaoPortuguesIngles() {
		Assert.assertEquals("love", Babelfish.traduzir(Babelfish.PORTUGUES_PARA_INGLES, "amor"));
	}

	@Test
	public void testarTraducaoInglesPortugues() {
		Assert.assertEquals("amor", Babelfish.traduzir(Babelfish.INGLES_PARA_PORTUGUES, "love"));
	}

}