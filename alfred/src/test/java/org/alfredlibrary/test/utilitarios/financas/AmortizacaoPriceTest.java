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

import org.alfredlibrary.utilitarios.financas.AmortizacaoPrice;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário AmortizacaoPrice.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public class AmortizacaoPriceTest {
	
	@Test
	public void obter() {
		double[][] esperado = new double[][] {
				{0, 0, 0, 30000},
				{790.02, 300.00, 490.02, 29509.98},
				{790.02, 295.10, 494.92, 29015.07},
				{790.02, 290.15, 499.86, 28515.21},
				{790.02, 285.15, 504.86, 28010.34},
				{790.02, 280.10, 509.91, 27500.43},
				{790.02, 275.00, 515.01, 26985.42},
				{790.02, 269.85, 520.16, 26465.26},
				{790.02, 264.65, 525.36, 25939.90},
				{790.02, 259.40, 530.62, 25409.28},
				{790.02, 254.09, 535.92, 24873.36},
				{790.02, 248.73, 541.28, 24332.08},
				{790.02, 243.32, 546.69, 23785.38},
				{790.02, 237.85, 552.16, 23233.22},
				{790.02, 232.33, 557.68, 22675.54},
				{790.02, 226.76, 563.26, 22112.28},
				{790.02, 221.12, 568.89, 21543.39},
				{790.02, 215.43, 574.58, 20968.81},
				{790.02, 209.69, 580.33, 20388.48},
				{790.02, 203.88, 586.13, 19802.35},
				{790.02, 198.02, 591.99, 19210.36},
				{790.02, 192.10, 597.91, 18612.44},
				{790.02, 186.12, 603.89, 18008.55},
				{790.02, 180.09, 609.93, 17398.62},
				{790.02, 173.99, 616.03, 16782.60},
				{790.02, 167.83, 622.19, 16160.41},
				{790.02, 161.60, 628.41, 15532.00},
				{790.02, 155.32, 634.70, 14897.30},
				{790.02, 148.97, 641.04, 14256.26},
				{790.02, 142.56, 647.45, 13608.81},
				{790.02, 136.09, 653.93, 12954.88},
				{790.02, 129.55, 660.47, 12294.41},
				{790.02, 122.94, 667.07, 11627.34},
				{790.02, 116.27, 673.74, 10953.60},
				{790.02, 109.54, 680.48, 10273.12},
				{790.02, 102.73, 687.28, 9585.84},
				{790.02, 95.86, 694.16, 8891.68},
				{790.02, 88.92, 701.10, 8190.58},
				{790.02, 81.91, 708.11, 7482.47},
				{790.02, 74.82, 715.19, 6767.28},
				{790.02, 67.67, 722.34, 6044.94},
				{790.02, 60.45, 729.57, 5315.38},
				{790.02, 53.15, 736.86, 4578.51},
				{790.02, 45.79, 744.23, 3834.28},
				{790.02, 38.34, 751.67, 3082.61},
				{790.02, 30.83, 759.19, 2323.42},
				{790.02, 23.23, 766.78, 1556.64},
				{790.02, 15.57, 774.45, 782.19},
				{790.02, 7.82, 782.19, 0}
		};
		
		double [][] realizado = AmortizacaoPrice.obter(30000, 1, 48, true);
		if (esperado.length != realizado.length) {
			Assert.fail();
		} else {
			for (int linha = 0; linha < realizado.length; linha++) {
				if (esperado[linha].length != realizado[linha].length) {
					Assert.fail();
				} else {
					for (int coluna = 0; coluna < realizado[linha].length; coluna++) {
						if (esperado[linha][coluna] != Double.valueOf(String.valueOf(Math.round(realizado[linha][coluna] * 100))) / 100) {
							System.out.println("Parcela " + linha + ": " + esperado[linha][coluna] + " vs " + Double.valueOf(String.valueOf(Math.round(realizado[linha][coluna] * 100))) / 100);
							Assert.fail();
						}
					}
				}
			}
		}
	}
	
}