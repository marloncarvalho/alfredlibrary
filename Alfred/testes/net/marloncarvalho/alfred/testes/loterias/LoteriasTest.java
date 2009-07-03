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
package net.marloncarvalho.alfred.testes.loterias;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.loterias.Loterias;

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
		try {
			String[] resultado = Loterias.obterResultadoMegaSena("1087");
			if ( resultado.length < 6 )
				Assert.fail();
			for(int i=0; i<resultado.length;i++) {
				System.out.println(resultado[i]);
				if ( resultado[i] == null || "".equals(resultado[i]) ) 
					Assert.fail();
			}
		} catch ( AlfredException exc ) {
			Assert.fail();
		}
	}

	@Test
	public void testarResultadoQuina() {
		try {
			String[] resultado = Loterias.obterResultadoQuina("2072");
			if ( resultado.length < 5 )
				Assert.fail();
			for(int i=0; i<resultado.length;i++) {
				System.out.println(resultado[i]);
				if ( resultado[i] == null || "".equals(resultado[i]) ) 
					Assert.fail();
			}
		} catch ( AlfredException exc ) {
			Assert.fail();
		}
	}
}