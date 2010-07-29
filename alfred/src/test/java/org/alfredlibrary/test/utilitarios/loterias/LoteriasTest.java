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
package org.alfredlibrary.test.utilitarios.loterias;

import org.alfredlibrary.utilitarios.loterias.Loterias;
import org.junit.Assert;
import org.junit.Test;

/**
 * Teste dos utilitários de Loterias.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/07/2009
 */
public class LoteriasTest {

	@Test
	public void testarResultadoMegaSena() {
		String[] resultado = Loterias.obterResultadoMegaSena("1");
		if (resultado.length < 6)
			Assert.fail();
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] == null || "".equals(resultado[i]))
				Assert.fail();
		}
	}

	@Test
	public void testarResultadoQuina() {
		String[] resultado = Loterias.obterResultadoQuina("2072");
		if (resultado.length < 5)
			Assert.fail();
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] == null || "".equals(resultado[i]))
				Assert.fail();
		}
	}

	@Test
	public void testarResultadoLotofacil() {
		String[] resultado = Loterias.obterResultadoLotofacil("1");
		if (resultado.length < 15)
			Assert.fail();
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] == null || "".equals(resultado[i]))
				Assert.fail();
		}
	}

	@Test
	public void testarResultadoLotomania() {
		String[] resultado = Loterias.obterResultadoLotomania("1030");
		if (resultado.length < 20)
			Assert.fail();
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] == null || "".equals(resultado[i]))
				Assert.fail();
		}
	}

	@Test
	public void testarResultadoDuplasena() {
		String[][] resultado = Loterias.obterResultadoDuplaSena("857");
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[i].length; j++) {
				if (resultado[i][j] == null || "".equals(resultado[i][j]))
					Assert.fail();
			}
		}
	}

}