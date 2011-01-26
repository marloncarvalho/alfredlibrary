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
package org.alfredlibrary.test.utilitarios.correios;

import java.util.List;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.correios.CEP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste para CEP.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class CEPTest {

	@Test
	public void testarConsultarEnderecoCorretoCEPLivre() {
		String[] endereco = CEP.consultarEnderecoCEPLivre("40290280");
		Assert.assertEquals(6,endereco.length);
		for(int i=0; i < endereco.length; i++) {
			Assert.assertNotNull(endereco[i]);
		}
		Assert.assertEquals("CRUZ E SOUZA", endereco[1].toUpperCase());
		Assert.assertEquals("ACUPE", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
	}

	@Test
	public void testarConsultarEnderecoIncorretoCEPLivre() {
		try {
			CEP.consultarEnderecoCEPLivre("11111111");
			Assert.fail();
		} catch ( AlfredException ae) {
		}
	}

	@Test
	public void testarConsultarEnderecoCorretoCorreios() {
		String[] endereco = CEP.consultarEnderecoCorreios("40290280");
		Assert.assertEquals(5,endereco.length);
		for(int i=0; i < endereco.length; i++) {
			Assert.assertNotNull(endereco[i]);
		}
		Assert.assertEquals("RUA CRUZ E SOUZA", endereco[1].toUpperCase());
		Assert.assertEquals("ACUPE DE BROTAS", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
	}

	@Test
	public void testarConsultarEnderecoIncorretoCorreios() {
		try {
			CEP.consultarEnderecoCorreios("11111111");
			Assert.fail();
		} catch ( AlfredException ae) {
		}
	}

	@Test
	public void testarConsultarEnderecoCorretoCorreiosLogradouro() {
		String[] endereco = CEP.consultarEnderecoCorreios("Rodolpho Coelho Cavalcante");
		Assert.assertEquals(5,endereco.length);
		for(int i=0; i < endereco.length; i++) {
			Assert.assertNotNull(endereco[i]);
		}
		Assert.assertEquals("RUA RODOLPHO COELHO CAVALCANTE", endereco[1].toUpperCase());
		Assert.assertEquals("ARMAÇÃO", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41750166", endereco[0].toUpperCase());
	}
	
	@Test
	public void testarConsultaEnderecosCEPLivreLogradouro() {
		List<String[]> listEndereco = CEP.consultarEnderecoCEPLivreLogradouro("tancredo neves", null, "salvador");
		
		String[] endereco = listEndereco.get(0);
		Assert.assertEquals("AV.", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("CAMINHO DAS ÁRVORES", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41820-020", endereco[5].toUpperCase());
		
		endereco = listEndereco.get(1);
		Assert.assertEquals("AV.", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("PERNAMBUÉS", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41100-800", endereco[5].toUpperCase());
		
		endereco = listEndereco.get(2);
		Assert.assertEquals("TRAV.", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41207-695", endereco[5].toUpperCase());
		
		endereco = listEndereco.get(3);
		Assert.assertEquals("AV.", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("CAMINHO DAS ÁRVORES", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41820-021", endereco[5].toUpperCase());
		
		endereco = listEndereco.get(4);
		Assert.assertEquals("RUA", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("PALESTINA", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41308-325", endereco[5].toUpperCase());
		
		endereco = listEndereco.get(5);
		Assert.assertEquals("AV.", endereco[0].toUpperCase());
		Assert.assertEquals("TANCREDO NEVES", endereco[1].toUpperCase());
		Assert.assertEquals("CAMINHO DAS ÁRVORES", endereco[2].toUpperCase());
		Assert.assertEquals("SALVADOR", endereco[3].toUpperCase());
		Assert.assertEquals("BA", endereco[4].toUpperCase());
		Assert.assertEquals("41820-901", endereco[5].toUpperCase());
	}
	 
}