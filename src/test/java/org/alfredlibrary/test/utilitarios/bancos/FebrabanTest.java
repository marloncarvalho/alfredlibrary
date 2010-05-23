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
package org.alfredlibrary.test.utilitarios.bancos;

import java.util.Collection;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.bancos.Banco;
import org.alfredlibrary.utilitarios.bancos.Febraban;
import org.alfredlibrary.utilitarios.bancos.Naturalidade;
import org.alfredlibrary.utilitarios.bancos.Origem;
import org.alfredlibrary.utilitarios.bancos.Tipo;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário de Bancos da Febraban.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/06/2010
 */
public class FebrabanTest {

	@Test
	public void testObter() {
		try {
			Collection<Banco> bancos = Febraban.obterListaBancos(Tipo.CAIXA, Origem.TODOS, Naturalidade.TODOS);
			for(Banco banco:bancos) {
				if ( "CAIXA ECONOMICA FEDERAL".equals(banco.getNome()) ) {
					return;
				}
			}
			Assert.fail();
		} catch (Exception e) {
			Assert.fail();
		}
	}

}