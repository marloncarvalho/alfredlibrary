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
import org.alfredlibrary.formatadores.Moeda.MoedaLocal;
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
	
	@Test
	public void testFormatacaoPorLocal() {
		Assert.assertEquals("R$ 1.000,99", Moeda.formatar(1000.99d, MoedaLocal.BRASIL));
		Assert.assertEquals("R$ 1.001,00", Moeda.formatar(1000.9999d, MoedaLocal.BRASIL));
		Assert.assertEquals("R$ 1.000,99", Moeda.formatar(1000.991d, MoedaLocal.BRASIL));
		Assert.assertEquals("$ 1,000.99", Moeda.formatar(1000.99d, MoedaLocal.ESTADOS_UNIDOS));
		Assert.assertEquals("$ 1,001.00", Moeda.formatar(1000.9999d, MoedaLocal.ESTADOS_UNIDOS));
		Assert.assertEquals("$ 1,000.99", Moeda.formatar(1000.991d, MoedaLocal.ESTADOS_UNIDOS));
	}
	
	@Test
	public void testFormatacaoPorLocalEFormato() {
		Assert.assertEquals("R$ 1000", Moeda.formatar(1000d, MoedaLocal.BRASIL, "¤ ########0"));
		Assert.assertEquals("R$ 1001", Moeda.formatar(1000.99d, MoedaLocal.BRASIL, "¤ ########0"));
		Assert.assertEquals("R$ 1000", Moeda.formatar(1000.1d, MoedaLocal.BRASIL, "¤ ########0"));
		Assert.assertEquals("$ 1000", Moeda.formatar(1000d, MoedaLocal.ESTADOS_UNIDOS, "¤ ########0"));
		Assert.assertEquals("$ 1001", Moeda.formatar(1000.99d, MoedaLocal.ESTADOS_UNIDOS, "¤ ########0"));
		Assert.assertEquals("$ 1000", Moeda.formatar(1000.1d, MoedaLocal.ESTADOS_UNIDOS, "¤ ########0"));
	}

}