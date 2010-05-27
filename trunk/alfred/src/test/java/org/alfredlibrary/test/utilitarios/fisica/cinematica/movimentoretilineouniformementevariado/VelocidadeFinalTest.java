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
package org.alfredlibrary.test.utilitarios.fisica.cinematica.movimentoretilineouniformementevariado;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoretilineouniformementevariado.VelocidadeFinal;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário AceleracaoCentripeta.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public class VelocidadeFinalTest {
	
	@Test
	public void calcular() {
		Assert.assertEquals(9d, VelocidadeFinal.calcular(5d, 2d, 2d));
	}
	
}