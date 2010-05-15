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
package org.alfredlibrary.test.utilitarios.matematica;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.matematica.Matriz;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário Matriz.
 * 
 * @author Marlon Silva Carvalho
 * @since 14/05/2010
 */
public class MatrizTest {

	@Test
	public void testTranspor() {
		Double[][] matriz = new Double[][] { {1d,1d}, {2d,2d}, {3d,3d} };
		Double[][] transposta = null;
		try {
			transposta = Matriz.transpor(matriz);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(transposta);
		Assert.assertEquals(3, transposta[0].length);
		Assert.assertEquals(2, transposta.length);
		Assert.assertEquals(1d, transposta[0][0]);
		Assert.assertEquals(1d, transposta[1][0]);
		Assert.assertEquals(2d, transposta[0][1]);
		Assert.assertEquals(2d, transposta[1][1]);
		Assert.assertEquals(3d, transposta[0][2]);
		Assert.assertEquals(3d, transposta[1][2]);
	}

	@Test
	public void testSomarErrado() {
		try {
			Matriz.somar(new Double[][] {{1d,1d}}, new Double[][] {{2d,2d},{3d,3d}});
			Assert.fail();
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSomarCerto() {
		try {
			Double[][] soma = Matriz.somar(new Double[][] {{1d,1d}, {1d,1d}}, new Double[][] {{1d,1d},{1d,1d}});
			Assert.assertEquals(2d, soma[0][0]);
			Assert.assertEquals(2d, soma[0][1]);
			Assert.assertEquals(2d, soma[1][0]);
			Assert.assertEquals(2d, soma[1][1]);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testSubtrairErrado() {
		try {
			Matriz.subtrair(new Double[][] {{1d,1d}}, new Double[][] {{2d,2d},{3d,3d}});
			Assert.fail();
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSubtrairCerto() {
		try {
			Double[][] soma = Matriz.subtrair(new Double[][] {{1d,1d}, {1d,1d}}, new Double[][] {{1d,1d},{1d,1d}});
			Assert.assertEquals(0d, soma[0][0]);
			Assert.assertEquals(0d, soma[0][1]);
			Assert.assertEquals(0d, soma[1][0]);
			Assert.assertEquals(0d, soma[1][1]);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}