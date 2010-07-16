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
package org.alfredlibrary.test.utilitarios.texto;

import org.alfredlibrary.utilitarios.texto.Texto;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário de Texto.
 * 
 * @author Marlon Silva Carvalho
 * @since 14/05/2010
 */
public class TextoTest {

	@Test
	public void testManterNumeros() {
		try {
			String numeros = Texto.manterNumeros("t12r3a4c5e6b78g9c0");
			Assert.assertEquals("1234567890", numeros);
		} catch (Throwable t) {
			Assert.fail(t.getMessage());
		}
	}

	@Test
	public void testIncluirCaracterInicio() {
		try {
			String texto = Texto.incluirCaracterInicio("bcdefghij", 'a', 2);
			Assert.assertEquals("aabcdefghij", texto);
		} catch (Throwable t) {
			Assert.fail(t.getMessage());
		}		
	}

	@Test
	public void testCapitalizarIniciais() {
		try {
			String texto = Texto.capitalizarIniciais("marlon silva carvalho");
			Assert.assertEquals("Marlon Silva Carvalho", texto);
		} catch (Throwable t) {
			Assert.fail(t.getMessage());
		}				
	}
	
	@Test
	public void testContarQuantidadeVezes() {
		try {
			int q = Texto.contarQuantidadePalavra("marlon teste marlon alguma coisa a mais marlon", "marlon", true);
			Assert.assertEquals(3, q);
		} catch (Throwable t) {
			Assert.fail(t.getMessage());
		}
	}

	@Test
	public void testTrocarCaracteresAcentuados() {
		try {
			Assert.assertEquals("a e i o u a e i o u a e i o u a e i o u", Texto.trocarCaracteresAcentuados("â ê í ó ú á è ì ò ù ä ë ĩ õ ũ ã é ï ö ü"));
		} catch ( Exception e ) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testRemoverPontuacao() {
		try {
			Assert.assertEquals("", Texto.removerPontuacao("-!,.:;?/\b\t\n\f\r\"\'\\"));
		} catch ( Exception e ) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testComparar() {
		try {
			if (Texto.comparar("lotadotodoobrasilesta", "Lotado, todo o Brasil está aqui!", true, true, true, true) >= 0) {
				Assert.fail();
			}
			if (Texto.comparar("lotadotodoobrasilestaaqui", "Lotado, todo o Brasil está aqui!", true, true, true, true) != 0) {
				Assert.fail();
			}
			if (Texto.comparar("lotadotodoobrasilestaaquidentro", "Lotado, todo o Brasil está aqui!", true, true, true, true) <= 0) {
				Assert.fail();
			}
		} catch ( Exception e ) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testCompletar() {
		Assert.assertEquals("XXXXXTESTE", Texto.completar("TESTE", 'X', 10, false));
		Assert.assertEquals("TESTEXXXXX", Texto.completar("TESTE", 'X', 10, true));
		Assert.assertEquals("99999TESTE", Texto.completar("TESTE", '9', 10, false));
		Assert.assertEquals("TESTE99999", Texto.completar("TESTE", '9', 10, true));
	}

}