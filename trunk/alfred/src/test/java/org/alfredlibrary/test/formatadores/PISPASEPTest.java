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
import org.alfredlibrary.formatadores.PISPASEP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Formatador de PIS/PASEP.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 08/06/2010
 */
public class PISPASEPTest {

	@Test
	public void testarFormatacao() {
		String pisPasep = PISPASEP.formatar("11111111114");
		Assert.assertEquals(pisPasep.charAt(3), '.');
		Assert.assertEquals(pisPasep.charAt(9), '.');
		Assert.assertEquals(pisPasep.charAt(12), '-');
	}
	
	@Test
	public void testarFormatacaoTamanhoMenor() {
		try {
			PISPASEP.formatar("1111111111");
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void testarFormatacaoTamanhoMaior() {
		try {
			PISPASEP.formatar("111111111111");
		} catch (AlfredException ae) {
		}
	}

}