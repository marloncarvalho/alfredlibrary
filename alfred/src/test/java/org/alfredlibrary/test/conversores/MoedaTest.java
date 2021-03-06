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
package org.alfredlibrary.test.conversores;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.conversores.Moeda;
import org.junit.Test;

/**
 * Teste de Moeda.
 * 
 * @author Marlon Silva Carvalho
 * @since 30/06/2009
 */
public class MoedaTest {

	@Test
	public void testarConversao() {
		try {
			Moeda.converter("1,00", Moeda.ESTADOSUNIDOS, Moeda.BRASIL);
		} catch ( AlfredException ex ) {
			Assert.fail();
		}
	}

}