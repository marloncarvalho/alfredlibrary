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

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.correios.CEP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste para CEP.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class CEPTest {

	/**
	 * Testar a formatação de um CEP.
	 */
	@Test
	public void testarFormatacaoCEP() {
		String cep = CEP.formatar("40290280");
		if ( cep.charAt(2) != '.' )
			Assert.fail();
		if ( cep.charAt(6) != '-' )
			Assert.fail();
	}

	/**
	 * Testar um CEP incorreto.
	 * Deve lançar exceção.
	 */
	@Test
	public void testarCEPIncorreto() {
		try {
			CEP.formatar("410290280");
			Assert.fail();
		} catch (AlfredException exception) {

		}
	}
	
	/**
	 * Testar a obtenção de um Endereço por um CEP correto.
	 */
	@Test
	public void testarConsultarEnderecoCorreto() {
		String[] endereco = CEP.consultarEndereco("40290280");
		// São 6 posições sempre.
		if ( endereco.length != 6 )
			Assert.fail();
		for(int i=0; i < endereco.length; i++) {
			if ( endereco[i] == null )
				Assert.fail();
		}
	}

	/**
	 * Testar a consulta de um CEP incorreto.
	 */
	@Test
	public void testarConsultarEnderecoIncorreto() {
		try {
			CEP.consultarEndereco("11111111");
			Assert.fail();
		} catch ( AlfredException ae) {
		}
	}

}