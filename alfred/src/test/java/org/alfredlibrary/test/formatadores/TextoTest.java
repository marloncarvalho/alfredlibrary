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

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.formatadores.Texto;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para formatador de Textos.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 12/04/2011
 */
public class TextoTest {

	@Test
	public void testFormatacao() {
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB;:87X", "AA.XX/99-D", true));
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB;:87X", "AA.XX/99-D", false));
	}

	@Test
	public void testFormatacaoTextoMenor() {
		Assert.assertEquals("AB.;:", Texto.formatar("AB;:", "AA.XX/99-D", true));
		Assert.assertEquals("87-X", Texto.formatar("87X", "AA.XX/99-D", false));
	}
	
	@Test
	public void testFormatacaoTextoMaior() {
		try {
			@SuppressWarnings("unused")
			String texto = Texto.formatar("ABC;:87X", "AA.XX/99-D", true);
			Assert.fail();
		} catch (AlfredException ae) {}
	}

	@Test
	public void testFormatacaoTextoJaFormatado() {
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB.;:/87-X", "AA.XX/99-D", true));
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB.;:/87-X", "AA.XX/99-D", false));
	}
	
	@Test
	public void testFormatacaoTextoSemiFormatado() {
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB.;:87X", "AA.XX/99-D", true));
		Assert.assertEquals("AB.;:/87-X", Texto.formatar("AB;:/87X", "AA.XX/99-D", false));
	}
	
	@Test
	public void testFormatacaoErro() {
		@SuppressWarnings("unused")
		String textoAux;
		try {
			textoAux = Texto.formatar("!?;:87X", "AA.XX/99-D", true);
			Assert.fail();
		} catch (AlfredException ae) {}
		try {
			textoAux = Texto.formatar("AB;:EFX", "AA.XX/99-D", true);
			Assert.fail();
		} catch (AlfredException ae) {}
		try {
			textoAux = Texto.formatar("AB;:87H", "AA.XX/99-D", true);
			Assert.fail();
		} catch (AlfredException ae) {}
	}

}