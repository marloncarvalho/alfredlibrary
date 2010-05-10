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

import org.alfredlibrary.conversores.Comprimento;
import org.alfredlibrary.conversores.Comprimento.Unidade;
import org.junit.Test;

/**
 * Teste da conversão de Comprimento.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class ComprimentoTest {

	@Test
	public void testarConversaoQuilometroMetro() {
		Assert.assertEquals(1000D, Comprimento.converter(1, Unidade.KM, Unidade.METRO));
	}

	@Test
	public void testarConversaoMetroDecimetro() {
		Assert.assertEquals(10D, Comprimento.converter(1, Unidade.METRO, Unidade.DECIMETRO));
	}

	@Test
	public void testarConversaoDecimetroCentimetro() {
		Assert.assertEquals(10D, Comprimento.converter(1, Unidade.DECIMETRO, Unidade.CENTIMETRO));
	}

	@Test
	public void testarConversaoCentimetroMilimetro() {
		Assert.assertEquals(10D, Comprimento.converter(1, Unidade.CENTIMETRO, Unidade.MILIMETRO));
	}

	@Test
	public void testarConversaoMilimetroMicrometro() {
		Assert.assertEquals(1000D, Comprimento.converter(1, Unidade.MILIMETRO, Unidade.MICROMETRO));
	}

	@Test
	public void testarConversaoMicrometroNanometro() {
		Assert.assertEquals(1000D, Comprimento.converter(1, Unidade.MICROMETRO, Unidade.NANOMETRO));
	}

	@Test
	public void testarConversaoNanometroAngstrom() {
		Assert.assertEquals(10D, Comprimento.converter(1, Unidade.NANOMETRO, Unidade.ANGSTROM));
	}

}