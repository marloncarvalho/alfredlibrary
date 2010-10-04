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
package org.alfredlibrary.test.utilitarios.financas;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.financas.AmortizacaoSAC;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário AmortizacaoSAC.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public class AmortizacaoSACTest {
	
	@Test
	public void obter() {
		double[][] esperado = new double[][] {
				{0, 0, 0, 120000},
				{11200, 1200, 10000, 110000},
				{11100, 1100, 10000, 100000},
				{11000, 1000, 10000, 90000},
				{10900, 900, 10000, 80000},
				{10800, 800, 10000, 70000},
				{10700, 700, 10000, 60000},
				{10600, 600, 10000, 50000},
				{10500, 500, 10000, 40000},
				{10400, 400, 10000, 30000},
				{10300, 300, 10000, 20000},
				{10200, 200, 10000, 10000},
				{10100, 100, 10000, 0}
		};
		double [][] realizado = AmortizacaoSAC.obter(120000, 1, 12, true);
		if (esperado.length != realizado.length) {
			Assert.fail();
		} else {
			for (int linha = 0; linha < realizado.length; linha++) {
				if (esperado[linha].length != realizado[linha].length) {
					Assert.fail();
				} else {
					for (int coluna = 0; coluna < realizado[linha].length; coluna++) {
						if (esperado[linha][coluna] != realizado[linha][coluna]) {
							System.out.println(esperado[linha][coluna] + " vs " + realizado[linha][coluna]);
							Assert.fail();
						}
					}
				}
			}
		}
	}
	
}