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
package net.marloncarvalho.alfred.testes.net;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import net.marloncarvalho.alfred.AlfredConfig;
import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.net.WorldWideWeb;

import org.junit.Test;

/**
 * Teste da classe de utilitário WorldWideWeb.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/07/2009
 */
public class WorldWideWebTest {

	@Test
	public void testarObterConteudoSemPostComProxy() {
		try {
			AlfredConfig.getInstancia().setUsingProxy(true);
			AlfredConfig.getInstancia().setProxy("", 80);
			String conteudo = WorldWideWeb.getConteudoSite("http://alfredlibrary.googlecode.com/");
			if ( conteudo == null || "".equals(conteudo ) ) {
				Assert.fail();
			}
		} catch ( AlfredException ae ) {
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test
	public void testarObterConteudoSemPost() {
		try {
			String conteudo = WorldWideWeb.getConteudoSite("http://alfredlibrary.googlecode.com/");
			if ( conteudo == null || "".equals(conteudo ) ) {
				Assert.fail();
			}
		} catch ( AlfredException ae ) {
			Assert.fail(ae.getMessage());
		}
	}

	@Test
	public void testarObterConteudoComPost() {
		try {
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("teste","1");
			String conteudo = WorldWideWeb.getConteudoSite("http://alfredlibrary.googlecode.com/",mapa);
			if ( conteudo == null || "".equals(conteudo ) ) {
				Assert.fail();
			}
		} catch ( AlfredException ae ) {
			Assert.fail(ae.getMessage());
		}
	}

	@Test
	public void testarObterConteudoArquivo() {
		try {
			InputStream conteudo = WorldWideWeb.getConteudoArquivo("http://code.google.com/p/alfredlibrary/logo?logo_id=1246636913");
			if ( conteudo == null || "".equals(conteudo ) ) {
				Assert.fail();
			}
		} catch ( AlfredException ae ) {
			Assert.fail(ae.getMessage());
		}
	}

}