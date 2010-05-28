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
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoparabolico.Tempo;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Tempo.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public class TempoTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(0.19999999999999998d, Tempo.calcular(2d, Math.PI / 6, 10d));
		Assert.assertEquals(1.1547005383792515d, Tempo.calcularPorAlcance(2d, Math.PI / 6, 2d));
	}
	
	@Test
	public void calcularDivisaoPorZero() {
		try {
			Tempo.calcular(2d, Math.PI / 6, 0d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
		try {
			Tempo.calcularPorAlcance(0d, Math.PI / 6, 2d);
			Assert.fail();
		} catch (AlfredException ae) {	
		}
	}
}