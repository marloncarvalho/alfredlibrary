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

import org.alfredlibrary.formatadores.CEP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o formatador de CEP.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/05/2010
 */
public class CEPTest {

	@Test
	public void testarFormatacaoCEP() {
		String cep = CEP.formatar("40290280", false);
		Assert.assertEquals("40.290-280", cep);
	}

	@Test
	public void testarFormatacaoCEPSimples() {
		String cep = CEP.formatar("40290280", true);
		Assert.assertEquals("40290-280", cep);
	}

}