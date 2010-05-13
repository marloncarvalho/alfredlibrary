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

import org.alfredlibrary.formatadores.CPF;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Formatador de CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/05/2010
 */
public class CPFTest {

	@Test
	public void testarFormatacao() {
		String cpf = CPF.formatar("11111111111");
		Assert.assertEquals(cpf.charAt(3), '.');
		Assert.assertEquals(cpf.charAt(7), '.');
		Assert.assertEquals(cpf.charAt(11), '-');
	}

}