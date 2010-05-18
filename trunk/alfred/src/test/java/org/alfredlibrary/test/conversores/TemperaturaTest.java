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

import junit.framework.Assert;

import org.alfredlibrary.conversores.Temperatura;
import org.alfredlibrary.conversores.Temperatura.Unidade;
import org.junit.Test;

/**
 * Teste de conversão de Temperatura.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class TemperaturaTest {

	@Test
	public void testarConverterCelsiusFahrenheit() {
		Assert.assertEquals(33.8F, Temperatura.converter(1, Unidade.CELSIUS, Unidade.FAHRENHEIT));
	}

	@Test
	public void testarConverterCelsiusKelvin() {
		Assert.assertEquals(274.15F, Temperatura.converter(1, Unidade.CELSIUS, Unidade.KELVIN));
	}

	@Test
	public void testarConverterFahrenheitCelsius() {
		Assert.assertEquals(-17.222221F, Temperatura.converter(1, Unidade.FAHRENHEIT, Unidade.CELSIUS));
	}
	
	@Test
	public void testarConverterFahrenheitKelvin() {
		Assert.assertEquals(255.92776F, Temperatura.converter(1, Unidade.FAHRENHEIT, Unidade.KELVIN));
	}

	@Test
	public void testarConverterKelvinCelsius() {
		Assert.assertEquals(-272.15F, Temperatura.converter(1, Unidade.KELVIN, Unidade.CELSIUS));
	}

	@Test
	public void testarConverterKelvinFahrenheit() {
		Assert.assertEquals(-457.86996F, Temperatura.converter(1, Unidade.KELVIN, Unidade.FAHRENHEIT));
	}

}