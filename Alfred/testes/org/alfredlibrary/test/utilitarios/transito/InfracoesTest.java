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
package org.alfredlibrary.test.utilitarios.transito;

import java.util.Collection;

import org.alfredlibrary.utilitarios.transito.Infracao;
import org.alfredlibrary.utilitarios.transito.Infracoes;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de Infrações.
 * 
 * @author Marlon Silva Carvalho
 * @since 14/05/2010
 */
public class InfracoesTest {

	@Test
	public void testObterInfracoes() {
		try {
			Collection<Infracao> infracoes = Infracoes.obterInfracoes();
			Assert.assertNotNull(infracoes);
			Assert.assertThat(infracoes.size(), IsNot.not(0));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}