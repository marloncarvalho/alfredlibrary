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
package org.alfredlibrary.test.validadores;

import junit.framework.Assert;

import org.alfredlibrary.validadores.Numeros;
import org.junit.Test;

/**
 * Classe de Teste para o Validador de Números.
 * 
 * @author Marlon Silva Carvalho
 * @since 15/05/2010
 */
public class NumerosTest {

	@Test
	public void testarInteiro() {
		Assert.assertTrue(Numeros.isInteger("1"));
		Assert.assertFalse(Numeros.isInteger("1.5"));
	}

	@Test
	public void testarShort() {
		Assert.assertTrue(Numeros.isShort("1"));
		Assert.assertFalse(Numeros.isShort("1.5"));
	}

	@Test
	public void testarLong() {
		Assert.assertTrue(Numeros.isLong("1"));
		Assert.assertFalse(Numeros.isLong("1.5"));
	}

	@Test
	public void testarFloat() {
		Assert.assertTrue(Numeros.isFloat("1.5"));
	}

	@Test
	public void testarDouble() {
		Assert.assertTrue(Numeros.isFloat("1.5"));
	}

	@Test
	public void testarBigDecimal() {
		Assert.assertTrue(Numeros.isBigDecimal("1.5"));
	}

}