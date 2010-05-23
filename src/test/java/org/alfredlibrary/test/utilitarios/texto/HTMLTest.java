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
package org.alfredlibrary.test.utilitarios.texto;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.texto.HTML;
import org.junit.Test;

/**
 * Classe de Teste do Utilitário de HTML.
 * 
 * @author Marlon Silva Carvalho
 * @since 14/05/2010
 */
public class HTMLTest {

	@Test
	public void testAcharLinks() {
		try {
			String[] links = HTML.acharLinks("<a href='http://alfredlibrary.googlecode.com/'>http://www.alfredlibrary.org/</a>");
			Assert.assertEquals("http://alfredlibrary.googlecode.com/", links[0]);
			Assert.assertEquals("http://www.alfredlibrary.org/", links[1]);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemoverTags() {
		try {
			String tags = HTML.removerTags("<center>centro</center><p>pê</p>");
			Assert.assertEquals("centropê", tags);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testDesconverterElementosHTMLEspeciais() {
		try {
			String texto = HTML.desconverterElementosHTMLEspeciais("n&atilde;o sei&nbsp;, &eacute; dif&iacute;cil.", 0);
			Assert.assertEquals("não sei , é difícil.", texto);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testConverterElementosEspeciais() {
		try {
			String texto = HTML.converterParaElementosHTMLEspeciais("não sei , é difícil.");
			Assert.assertEquals("n&atilde;o&nbsp;sei&nbsp;,&nbsp;&eacute;&nbsp;dif&iacute;cil.", texto);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}	
	}
}