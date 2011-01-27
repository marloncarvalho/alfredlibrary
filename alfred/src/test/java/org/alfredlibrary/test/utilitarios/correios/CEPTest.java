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
	public void testarConsultarEnderecosCEPLivreLogradouro() {
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
	
	@Test
	public void testarConsultarEnderecosCorreiosLogradouro() {
		List<String[]> listEndereco = CEP.consultarEnderecoCorreiosLogradouro("tancredo neves");
		
		String[] endereco = listEndereco.get(0);
		Assert.assertEquals("Rua 2 de Julho de Tancredo Neves (Cabula X)", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41205540", endereco[0]);
		
		endereco = listEndereco.get(1);
		Assert.assertEquals("Rua Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Manaus", endereco[3]);
		Assert.assertEquals("AM", endereco[4]);
		Assert.assertEquals("69087500", endereco[0]);
		
		endereco = listEndereco.get(2);
		Assert.assertEquals("Travessa Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Manaus", endereco[3]);
		Assert.assertEquals("AM", endereco[4]);
		Assert.assertEquals("69087230", endereco[0]);
		
		endereco = listEndereco.get(3);
		Assert.assertEquals("Avenida Sete de Setembro, 14", endereco[1]);
		Assert.assertEquals("Centro", endereco[2]);
		Assert.assertEquals("Presidente Tancredo Neves", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("45416970", endereco[0]);
		// Este endereço ainda tem um campo (Unidade: AC Presidente Tancredo Neves) que deve ser ignorado na montagem do endereço.
		
		endereco = listEndereco.get(4);
		Assert.assertEquals("1ª Travessa Senhor do Bonfim de Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41207688", endereco[0]);
		
		endereco = listEndereco.get(5);
		Assert.assertEquals("2ª Travessa Senhor do Bonfim de Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41207689", endereco[0]);
		
		endereco = listEndereco.get(6);
		Assert.assertEquals("Alameda Estrela de Tancredo Neves (Cj Hab Barreiras)", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41205283", endereco[0]);
		
		endereco = listEndereco.get(7);
		Assert.assertEquals("Avenida Tancredo Neves, 2915 Loja 4004", endereco[1]);
		Assert.assertEquals("Caminho das Árvores", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41820974", endereco[0]);
		// Este endereço ainda tem um campo (Unidade: ACF Avenida Tancredo Neves) que deve ser ignorado na montagem do endereço.
		
		endereco = listEndereco.get(8);
		Assert.assertEquals("Rua Direta de Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41205000", endereco[0]);
		
		endereco = listEndereco.get(9);
		Assert.assertEquals("Rua Senhor do Bonfim de Tancredo Neves", endereco[1]);
		Assert.assertEquals("Tancredo Neves", endereco[2]);
		Assert.assertEquals("Salvador", endereco[3]);
		Assert.assertEquals("BA", endereco[4]);
		Assert.assertEquals("41207687", endereco[0]);
	}
	 
}