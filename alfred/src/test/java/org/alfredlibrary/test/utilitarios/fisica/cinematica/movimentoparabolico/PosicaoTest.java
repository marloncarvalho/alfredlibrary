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
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoparabolico.Posicao;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Posição.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public class PosicaoTest {
	
	@Test
	public void calcular() {
		double[] posicaoOriginal = {0d,0d};
		double[] esperado = {22d,0.34641016151377546d};
		double[] resultado = Posicao.calcular(2d, Math.PI / 6, 10d, posicaoOriginal, 2d);
		Assert.assertEquals(esperado.length, resultado.length);
		for (int i = 0; i < resultado.length; i++) {
			Assert.assertEquals(esperado[i], resultado[i]);
		}
	}
	
	@Test
	public void calcularPlanoUniDimensional() {
		double[] posicaoOriginal = {0d};
		try {
			Posicao.calcular(2d, Math.PI / 6, 10d, posicaoOriginal, 2d);
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void calcularPlanoZeroDimensional() {
		double[] posicaoOriginal = {};
		try {
			Posicao.calcular(2d, Math.PI / 6, 10d, posicaoOriginal, 2d);
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void calcularPlanoTriDimensional() {
		double[] posicaoOriginal = {0d,0d,0d};
		try {
			Posicao.calcular(2d, Math.PI / 6, 10d, posicaoOriginal, 2d);
		} catch (AlfredException ae) {
		}
	}
				
}