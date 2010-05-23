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
package org.alfredlibrary.test.utilitarios.correios;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.correios.Sedex10;
import org.junit.Assert;
import org.junit.Test;

/**
 * Teste de Sedex 10.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
public class Sedex10Test {

	@Test
	public void testarPrecoPrazoCEPsCorretos() {
		try {
			String[] r = Sedex10.obterPrecoPrazoEntrega("40290280", "40290280", 1);
			Assert.assertEquals("R$ 18,20", r[0]);
			Assert.assertEquals("10 horas da manhã do dia útil seguinte", r[1]);
		} catch ( AlfredException exception ) {
			Assert.fail();
		}
	}

	@Test
	public void testarPrecoPrazoCEPsCEPOrigemIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290281", "40290280", 1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	@Test
	public void testarPrecoPrazoCEPsCEPDestinoIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290280", "40290281", 1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	@Test
	public void testarPrecoPrazoCEPsCorretosPesoIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290280", "40290280", -1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	@Test
	public void testarPrecoPrazoParametrosVazios() {
		try {
			Sedex10.obterPrecoPrazoEntrega("", "", -1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

}