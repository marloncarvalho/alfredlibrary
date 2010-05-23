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

import org.alfredlibrary.conversores.BaseNumerica;
import org.alfredlibrary.conversores.BaseNumerica.Base;
import org.junit.Assert;
import org.junit.Test;

/**
 * Teste de conversor de base numérica.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
public class BaseNumericaTest {

	@Test
	public void testarConversaoDecimalBinario() {
		String binario = BaseNumerica.converter("2", Base.DECIMAL, Base.BINARIO);
		Assert.assertEquals("10", binario);

		binario = BaseNumerica.converter("3", Base.DECIMAL, Base.BINARIO);
		Assert.assertEquals("11", binario);
	}

	@Test
	public void testarConversaoBinarioDecimal() {
		String binario = BaseNumerica.converter("10", Base.BINARIO, Base.DECIMAL);
		Assert.assertEquals("2",binario);
		binario = BaseNumerica.converter("11", Base.BINARIO, Base.DECIMAL);
		Assert.assertEquals("3",binario);
	}

	@Test
	public void testarConversaoDecimalHexadecimal() {
		String hexa = BaseNumerica.converter("10", Base.DECIMAL, Base.HEXADECIMAL);
		Assert.assertEquals("a", hexa);
		hexa = BaseNumerica.converter("1", Base.DECIMAL, Base.HEXADECIMAL);
		Assert.assertEquals("1", hexa);
		hexa = BaseNumerica.converter("11", Base.DECIMAL, Base.HEXADECIMAL);
		Assert.assertEquals("b", hexa);
	}

}