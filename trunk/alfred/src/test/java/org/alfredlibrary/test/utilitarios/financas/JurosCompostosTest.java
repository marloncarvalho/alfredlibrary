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

import org.alfredlibrary.utilitarios.financas.JurosCompostos;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário JurosCompostos.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 04/10/2010
 */
public class JurosCompostosTest {
	
	@Test
	public void calcularValorFuturo() {
		Assert.assertEquals(Math.round(112682.5030132D) / 100, Math.round(JurosCompostos.obterValorFuturo(1000D, 1D, 12D) * 100) / 100);
	}
	
	@Test
	public void calcularValorPresente() {
		Assert.assertEquals(Math.round(100000D) / 100, Math.round(JurosCompostos.obterValorPresente(1126.825030132D, 1D, 12D) * 100) / 100);
	}
	
	@Test
	public void calcularJuros() {
		Assert.assertEquals(Math.round(100D) / 100, Math.round(JurosCompostos.obterJuros(1000D, 1126.825030132D, 12D) * 100) / 100);
	}
		
	@Test
	public void calcularPeriodos() {
		Assert.assertEquals(Math.round(1200D) / 100, Math.round(JurosCompostos.obterPeriodos(1000D, 1126.825030132D, 1D) * 100) / 100);
	}
	
}