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
package net.marloncarvalho.alfred.testes.correios;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.correios.PAC;
import org.junit.Test;

/**
 * Classe de Teste para PAC.
 *  
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
public class PACTest {

	/**
	 * Testar PAC com dados corretos.
	 */
	@Test
	public void testarPrecoPrazoEntregaCaixaPacoteDadosCorretos() {
		try {
			PAC.obterPrecoPrazoEntregaParaCaixaPacote("40290280", "40290280", 1, 12, 6, 16);
		} catch (AlfredException ae) {
			Assert.fail();
		}
	}

	/**
	 * Testar PAC com dados corretos.
	 */
	@Test
	public void testarPrecoPrazoEntregaRoloPrismaDadosCorretos() {
		try {
			PAC.obterPrecoPrazoEntregaParaRoloPrisma("40290280", "40290280", 1, 6, 18);
		} catch (AlfredException ae) {
			Assert.fail();
		}
	}

}