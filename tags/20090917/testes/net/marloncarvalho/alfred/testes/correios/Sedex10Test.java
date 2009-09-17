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
package net.marloncarvalho.alfred.testes.correios;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.correios.Sedex10;

import org.junit.Assert;
import org.junit.Test;

/**
 * Teste de Sedex 10.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
public class Sedex10Test {

	/**
	 * Realizar o teste de Sedex para CEPs corretos.
	 * Não deve lançar exceção.
	 */
	@Test
	public void testarPrecoPrazoCEPsCorretos() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290280", "40290280", 1);
		} catch ( AlfredException exception ) {
			Assert.fail();
		}
	}

	/**
	 * Realizar o teste de Sedex para CEP Origem Incorreto.
	 * Deve lançar exceção.
	 */
	@Test
	public void testarPrecoPrazoCEPsCEPOrigemIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290281", "40290280", 1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	/**
	 * Realizar o teste de Sedex para CEP destino incorreto.
	 * Deve lançar exceção.
	 */
	@Test
	public void testarPrecoPrazoCEPsCEPDestinoIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290280", "40290281", 1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	/**
	 * Realizar o teste de Sedex para CEPs corretos e peso incorreto.
	 * Deve lançar exceção.
	 */
	@Test
	public void testarPrecoPrazoCEPsCorretosPesoIncorreto() {
		try {
			Sedex10.obterPrecoPrazoEntrega("40290280", "40290280", -1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

	/**
	 * Realizar o teste de Sedex para parâmetros vazios.
	 * Deve lançar exceção.
	 */
	@Test
	public void testarPrecoPrazoParametrosVazios() {
		try {
			Sedex10.obterPrecoPrazoEntrega("", "", -1);
			Assert.fail();
		} catch ( AlfredException exception ) {
		}
	}

}