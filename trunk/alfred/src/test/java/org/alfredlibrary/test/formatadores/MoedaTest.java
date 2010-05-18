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

import org.alfredlibrary.formatadores.Moeda;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Formatador de Moedas.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/06/2010
 */
public class MoedaTest {

	@Test
	public void testFormatacao() {
		Assert.assertEquals("DOIS REAIS", Moeda.formatar(2.0).toUpperCase());
		Assert.assertEquals("VINTE E UM REAIS E DEZ CENTAVOS", Moeda.formatar(21.10).toUpperCase());
		Assert.assertEquals("UM MILHÃO DE REAIS", Moeda.formatar(1000000.0).toUpperCase());
		Assert.assertEquals("UM MILHÃO E CEM MIL DE REAIS E DEZ CENTAVOS", Moeda.formatar(1100000.10).toUpperCase());
		Assert.assertEquals("DEZ MIL E CEM REAIS E DEZ CENTAVOS", Moeda.formatar(10100.10).toUpperCase());
	}

}