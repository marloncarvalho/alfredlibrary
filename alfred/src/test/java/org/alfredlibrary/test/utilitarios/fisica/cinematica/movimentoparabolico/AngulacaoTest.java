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
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoparabolico.Angulacao;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Angulacao.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public class AngulacaoTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(1.0159852938148253d, Angulacao.calcular(2d, 0.34d, 10d));
		Assert.assertEquals(0.8410686705679303d, Angulacao.calcularPorAlcance(2d, 2d, 1.5d));
		Assert.assertEquals(0.43896188560976074d, Angulacao.calcularPorGravidade(10d, 2d, 0.17d));
		Assert.assertEquals(0.5548110329800715d, Angulacao.calcularPorVelocidadeApice(1.7d, 2d));
		Assert.assertEquals(Math.PI / 6, Angulacao.calcularPorVelocidadeVerticalInicial(2 * Math.sin(Math.PI / 6), 2d));
	}
	
	@Test
	public void calcularDivisaoPorZero() {
		try {
			Angulacao.calcular(0d, 0.34d, 10d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorAlcance(0d, 2d, 1.5d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorAlcance(0d, 2d, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorAlcance(2d, 2d, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorGravidade(10d, 0d, 0.17d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorVelocidadeApice(1.7d, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Angulacao.calcularPorVelocidadeVerticalInicial(2 * Math.sin(Math.PI / 6), 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
}