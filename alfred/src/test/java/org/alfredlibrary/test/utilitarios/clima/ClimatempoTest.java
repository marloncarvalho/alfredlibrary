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
package org.alfredlibrary.test.utilitarios.clima;

import java.util.Collection;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.clima.Clima;
import org.alfredlibrary.utilitarios.clima.climatempo.Climatempo;
import org.junit.Test;

/**
 * Classe de teste para o utilitário de Clima da Climatempo.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/05/2010
 */
public class ClimatempoTest {

	@Test
	public void testObter() {
		try {
			Collection<Clima> climas = Climatempo.obterClima("800");
			if ( climas == null || climas.size() <= 0 ) {
				Assert.fail("Não foram encontrados climas para a localidade 857. Deveria existir!");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}