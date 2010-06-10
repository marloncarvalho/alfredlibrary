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
package org.alfredlibrary.test.utilitarios.pispasep;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.pispasep.PISPASEP;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de PIS/PASEP.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 08/06/2010
 */
public class PISPASEPTest {

	@Test
	public void testGerarDigitoVerificador() {
		String digito = PISPASEP.gerarDigitoVerificador("1111111111");
		Assert.assertEquals("6", digito);
	}
	
	@Test
	public void testGerarPISPASEP() {
		String pisPasep = PISPASEP.gerar();
		Assert.assertTrue(org.alfredlibrary.validadores.PISPASEP.isValido(pisPasep));
	}

}