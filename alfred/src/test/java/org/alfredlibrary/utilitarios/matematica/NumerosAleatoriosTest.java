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
package org.alfredlibrary.utilitarios.matematica;

import java.util.List;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

/**
 * 
 * Classe de Teste para o Gerador de Numeros Aleatórios.
 * 
 * @author Mario Jorge Pereira
 *  @since 25/12/2010
 * 
 */
public class NumerosAleatoriosTest {

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#booleanAleatorio()}
	 * .
	 */
	@Test
	public final void testBooleanAleatorio() {
		for(int i = 0 ; i< 10 ; i++){
			Boolean b = NumerosAleatorios.booleanAleatorio();
			Assert.assertTrue(b instanceof Boolean);
		}
		
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#inteiroAleatorio()}
	 * .
	 */
	@Test
	public final void testInteiroAleatorio() {
		for(int i = 0;i<10;i++){
			Integer numero = NumerosAleatorios.inteiroAleatorio();
			Assert.assertTrue(numero instanceof Integer);
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#inteirosAleatoriosEmUmaFaixaLimite(int, int)}
	 * .
	 */
	@Test
	public final void testInteirosAleatoriosEmUmaFaixaLimite() {
		int maximo = 10;
		int minimo = 5;
		for(int i = 0  ; i <100 ; i++){
			Integer valor = NumerosAleatorios.inteirosAleatoriosEmUmaFaixaLimite(minimo, maximo);
			Assert.assertTrue(((valor>=minimo)&&(valor<maximo)));
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#inteirosAleatoriosPositivosLimite(int)}
	 * .
	 */
	@Test
	public final void testInteirosAleatoriosPositivosLimite() {
		int limite = 20;
		for(int i = 0  ; i <100 ; i++){
			Integer valor = NumerosAleatorios.inteirosAleatoriosPositivosLimite(limite);
			Assert.assertTrue(((valor>=0)&&(valor<limite)));
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#longAleatorio()}.
	 */
	@Test
	public final void testLongAleatorio() {
		for(int i = 0;i<10;i++){
			Long numero = NumerosAleatorios.longAleatorio();
			Assert.assertTrue(numero instanceof Long);
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#floatAleatorio()}
	 * .
	 */
	@Test
	public final void testFloatAleatorio() {
		for(int i = 0;i<10;i++){
			Float numero = NumerosAleatorios.floatAleatorio();
			Assert.assertTrue(numero instanceof Float);
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#doubleAleatorio()}
	 * .
	 */
	@Test
	public final void testDoubleAleatorio() {
		for(int i = 0;i<10;i++){
			Double numero = NumerosAleatorios.doubleAleatorio();
			Assert.assertTrue(numero instanceof Double);
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#doubleAleatorioComDistribuicaoNormal()}
	 * .
	 */
	@Test
	public final void testDoubleAleatorioComDistribuicaoNormal() {
		for(int i = 0;i<10;i++){
			Double numero = NumerosAleatorios.doubleAleatorioComDistribuicaoNormal();
			Assert.assertTrue(numero instanceof Double);
		}
	}

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#inteirosAleatorios(double, double)}
	 * .
	 */
	@Test
	public final void testInteirosAleatorios() {
		double maximo = 10;
		double minimo = 5;
		for(int i = 0  ; i <100 ; i++){
			Double valor = NumerosAleatorios.inteirosAleatorios(minimo, maximo);
			Assert.assertTrue(((valor>=minimo)&&(valor<maximo)));
		}
	}
	
	
	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#listaInteirosAleatorios(int, double, double, boolean)}
	 * .
	 */
	@Test
	public final void testListaInteirosAleatorios() {
		List list = NumerosAleatorios.listaInteirosAleatorios(10, 3, 20, true);
		Assert.assertEquals("", 10, list.size());
	}
	

	/**
	 * Test method for
	 * {@link org.alfredlibrary.utilitarios.matematica.NumerosAleatorios#listaInteirosAleatorios(int, double, double, boolean)}
	 * .
	 */
	@Test
	public final void testListaInteirosAleatoriosFaixaMenorQueListaNaoRepetir() {
		try {
			List list = NumerosAleatorios.listaInteirosAleatorios(10, 3, 10, false);
			Assert.fail();
		}catch (AlfredException e) {
			
		}
			
	}
	
	
}
