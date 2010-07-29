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

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.correios.PAC;
import org.junit.Test;

/**
 * Classe de Teste para PAC.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
public class PACTest {

	@Test
	public void testarPrecoPrazoEntregaCaixaPacoteDadosCorretos() {
		String[] r = PAC.obterPrecoPrazoEntregaParaCaixaPacote("40290280",
				"40290280", 1, 12, 6, 16);
		Assert.assertEquals("R$ 10,50", r[0]);
		Assert.assertEquals("3 DIAS ÚTEIS", r[1]);
	}

	@Test
	public void testarPrecoPrazoEntregaRoloPrismaDadosCorretos() {
		String[] r = PAC.obterPrecoPrazoEntregaParaCaixaPacote("40290280",
				"40290280", 1, 12, 6, 16);
		Assert.assertEquals("R$ 10,50", r[0]);
		Assert.assertEquals("3 DIAS ÚTEIS", r[1]);
	}

}