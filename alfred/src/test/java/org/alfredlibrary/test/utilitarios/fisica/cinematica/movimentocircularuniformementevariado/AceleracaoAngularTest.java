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
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniformementevariado.AceleracaoAngular;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário EspacoFinal.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public class AceleracaoAngularTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(3d, AceleracaoAngular.calcular(10d, 4d, 2d));
		Assert.assertEquals(2d, AceleracaoAngular.calcular(10d, 4d, 21d, 0d));
	}
	
	@Test
	public void calcularDivisaoPorZero() {
		try {
			AceleracaoAngular.calcular(10d, 4d, 0d);
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void calcularObjetoEstatico() {
		try {
			AceleracaoAngular.calcular(10d, 4d, 21d, 21d);
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
		
}