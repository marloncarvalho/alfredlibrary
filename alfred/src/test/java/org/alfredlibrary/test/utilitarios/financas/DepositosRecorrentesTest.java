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

import org.alfredlibrary.utilitarios.financas.DepositosRecorrentes;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário DepositosRecorrentes.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public class DepositosRecorrentesTest {
	
	@Test
	public void obterSaldo() {
		Assert.assertEquals(621.35D, Double.valueOf(String.valueOf(Math.round(DepositosRecorrentes.obterSaldo(100D, 1D, 6D, false) * 100))) / 100);
	}
	
	@Test
	public void obterDeposito() {
		Assert.assertEquals(100D, Double.valueOf(String.valueOf(Math.round(DepositosRecorrentes.obterDeposito(621.35D, 1D, 6D, false) * 100))) / 100);
	}
	
	@Test
	public void obterPeriodoMinimo() {
		Assert.assertEquals(6, DepositosRecorrentes.obterPeriodoMinimo(100D, 621.35D, 1D, false));
		Assert.assertEquals(7, DepositosRecorrentes.obterPeriodoMinimo(100D, 621.35D, 1D, true));
	}
	
}