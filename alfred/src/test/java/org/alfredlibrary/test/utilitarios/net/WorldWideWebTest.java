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
package org.alfredlibrary.test.utilitarios.net;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.junit.Test;

/**
 * Teste da classe de utilitário WorldWideWeb.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/07/2009
 */
public class WorldWideWebTest {

//	@Test
//	public void testarObterConteudoSemPostComProxy() {
//		try {
//			AlfredConfig.getInstancia().setUsingProxy(true);
//			AlfredConfig.getInstancia().setProxy("189.56.61.33", 3128);
//			String conteudo = WorldWideWeb.getConteudoSite("http://alfredlibrary.googlecode.com/");
//			AlfredConfig.getInstancia().setUsingProxy(false);
//			if ( conteudo == null || "".equals(conteudo ) ) {
//				Assert.fail();
//			}
//		} catch ( AlfredException ae ) {
//			AlfredConfig.getInstancia().setUsingProxy(true);
//			Assert.fail(ae.getMessage());
//		}
//	}
//	
	@Test
	public void testarObterConteudoSemPost() {
		try {
			String conteudo = WorldWideWeb.obterConteudoSite("http://alfredlibrary.googlecode.com/");
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
			String conteudo = WorldWideWeb.obterConteudoSite("http://code.google.com/p/alfredlibrary/",mapa);
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
			InputStream conteudo = WorldWideWeb.obterConteudoArquivo("http://code.google.com/p/alfredlibrary/logo?logo_id=1246636913");
			if ( conteudo == null || "".equals(conteudo ) ) {
				Assert.fail();
			}
		} catch ( AlfredException ae ) {
			Assert.fail(ae.getMessage());
		}
	}

}