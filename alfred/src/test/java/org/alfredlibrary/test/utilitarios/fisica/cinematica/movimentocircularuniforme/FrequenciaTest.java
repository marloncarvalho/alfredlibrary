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
package org.alfredlibrary.test.utilitarios.fisica.cinematica.movimentocircularuniforme;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniforme.Frequencia;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Frequencia.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public class FrequenciaTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(0.5d, Frequencia.calcular(2d));
		Assert.assertEquals(0.5d, Frequencia.calcular(1, 2d));
	}
	
	@Test
	public void calcularDivisaoPorZeroPeriodo() {
		try {
			Frequencia.calcular(0d);
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void calcularDivisaoPorZeroTempo() {
		try {
			Frequencia.calcular(1, 0d);
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
}