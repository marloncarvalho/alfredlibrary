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
package net.marloncarvalho.alfred.testes.conversores;

import net.marloncarvalho.alfred.conversores.BaseNumerica;

import org.junit.Assert;
import org.junit.Test;

/**
 * Teste de conversor de base numérica.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
public class BaseNumericaTest {

	/**
	 * Testar a conversão de número decimal para binário.
	 */
	@Test
	public void testarConversaoDecimalBinario() {
		String binario = BaseNumerica.converterDecimalEmBinario("2");
		Assert.assertEquals("10", binario);

		binario = BaseNumerica.converterDecimalEmBinario("3");
		Assert.assertEquals("11", binario);
	}

	/**
	 * Testar a conversão de binário para decimal.
	 */
	@Test
	public void testarConversaoBinarioDecimal() {
		String binario = BaseNumerica.converterDecimalEmBinario("2");
		Assert.assertEquals("10",binario);
		binario = BaseNumerica.converterDecimalEmBinario("3");
		Assert.assertEquals("11",binario);
	}

	/**
	 * Testar conversão de decimal para hexadecimal.
	 */
	@Test
	public void testarConversaoDecimalHexadecimal() {
		String hexa = BaseNumerica.converterDecimalEmHexadecimal("10");
		Assert.assertEquals("0A", hexa);
		hexa = BaseNumerica.converterDecimalEmHexadecimal("1");
		Assert.assertEquals("01", hexa);
		hexa = BaseNumerica.converterDecimalEmHexadecimal("11");
		Assert.assertEquals("0B", hexa);
	}

}