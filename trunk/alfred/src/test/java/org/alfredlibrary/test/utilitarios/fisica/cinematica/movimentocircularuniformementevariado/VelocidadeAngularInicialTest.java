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
package org.alfredlibrary.test.utilitarios.fisica.cinematica.movimentocircularuniformementevariado;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniformementevariado.VelocidadeAngularInicial;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Raio.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public class VelocidadeAngularInicialTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(-2d, VelocidadeAngularInicial.calcular(4d, 3d, 2d));
		Assert.assertEquals(10d, VelocidadeAngularInicial.calcularPorAngulo(4d, 2d, 21d, 0d));
		Assert.assertEquals(4d, VelocidadeAngularInicial.calcularPorTempo(12d, 0d, 2d, 2d));
	}
	
	@Test
	public void calcularExcecaoIrracional() {
		try {
			VelocidadeAngularInicial.calcularPorAngulo(1d, 2d, 21d, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
	
	@Test
	public void calculardivisaoPorZero() {
		try {
			VelocidadeAngularInicial.calcularPorTempo(12d, 0d, 0d, 2d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
	
}