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
package org.alfredlibrary.test.utilitarios.fisica.cinematica.movimentoparabolico;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoparabolico.VelocidadeInicial;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário VelocidadeInicial.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public class VelocidadeInicialTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(2d, VelocidadeInicial.calcular(0.4d, 10d));
		Assert.assertEquals(1.4010677768204962d, VelocidadeInicial.calcular(0.17d,  Math.PI / 6, 10d));
		Assert.assertEquals(1.5396007178390019d, VelocidadeInicial.calcularPorAlcance(2d, Math.PI / 6, 1.5d));
		Assert.assertEquals(1.9629909152447274d, VelocidadeInicial.calcularPorApice(1.7d, Math.PI / 6));
		Assert.assertEquals(1.7000000000000004d, VelocidadeInicial.calcularPorGravidade(10d, Math.PI / 6, 0.17d));
		Assert.assertEquals(2.0000000000000004d, VelocidadeInicial.calcularPorVertical(1d, Math.PI / 6));
		Assert.assertEquals(0.9999999999999999d, VelocidadeInicial.calcularVertical(2d, Math.PI / 6));
	}
	
	@Test
	public void calcularDivisaoPorZero() {
		try {
			VelocidadeInicial.calcularPorAlcance(2d, Math.PI / 6, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
	
	@Test
	public void calcularResultadoIrracional() {
		try {
			VelocidadeInicial.calcular(0.17d,  Math.PI / 6, -10d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			VelocidadeInicial.calcular(0.4d, -10d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
}