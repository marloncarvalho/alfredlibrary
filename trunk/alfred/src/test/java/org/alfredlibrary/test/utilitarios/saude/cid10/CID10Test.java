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
package org.alfredlibrary.test.utilitarios.saude.cid10;

import java.util.Collection;
import java.util.Map;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.saude.cid10.CID10;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de buscas pelo CID-10.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 09/06/2010
 */
public class CID10Test {
	
	@Test
	public void testObterPorCodigo() {
		Collection<Map<String,Object>> resultado = CID10.obter("C18");
		Assert.assertEquals(1, resultado.size());
		for (Map<String, Object> map : resultado) {
			Assert.assertEquals("C18", map.get("codcat"));
			Assert.assertEquals("Neoplasia maligna do cólon", map.get("nome"));
			Assert.assertEquals("C18   Neopl malig do colon", map.get("nome50"));
		}
	}
	
	@Test
	public void testObterPorCodigoInvalido() {
		try {
			CID10.obter("C1");
			Assert.fail();
		} catch (AlfredException ae) {
		}
		try {
			CID10.obter("C1888");
			Assert.fail();
		} catch (AlfredException ae) {
		}
		try {
			CID10.obter("CC1888");
			Assert.fail();
		} catch (AlfredException ae) {
		}
	}
	
	@Test
	public void testObterPorCodigoMalFormatado() {
		try {
			Collection<Map<String,Object>> resultado = CID10.obter("Ç1.8-?+");
			Assert.assertEquals(1, resultado.size());
		} catch (Exception e) {
			Assert.fail();
		}		
	}
	
	@Test
	public void testObterPorCodigoComPonto() {
		try {
			Collection<Map<String,Object>> resultado = CID10.obter("C18.0");
			Assert.assertEquals(1, resultado.size());
		} catch (Exception e) {
			Assert.fail();
		}		
	}
	
	@Test
	public void testObterPorCodigoSemResultado() {
		try {
			Collection<Map<String,Object>> resultado = CID10.obter("C20.9");
			Assert.assertEquals(0, resultado.size());
		} catch (Exception e) {
			Assert.fail();
		}		
	}
	
	@Test
	public void testObterPorTextoInicial() {
		Collection<Map<String,Object>> resultado = CID10.obter("Neoplasia maligna do cól", true);
		Assert.assertEquals(7, resultado.size());
		for (Map<String, Object> map : resultado) {
			if (map.get("codcat") != null) {
				if (map.get("codcat").equals("C18")) {
					Assert.assertEquals("C18", map.get("codcat"));
					Assert.assertEquals("Neoplasia maligna do cólon", map.get("nome"));
					Assert.assertEquals("C18   Neopl malig do colon", map.get("nome50"));
				}
			} else if (map.get("codsubcat") != null) {
				if (!map.get("codsubcat").toString().contains("C18")) {
					Assert.fail();
				}
			}
		}
	}
	
	@Test
	public void testObterPorTextoIntermediario() {
		Collection<Map<String,Object>> resultado = CID10.obter("plasia maligna do cól", false);
		Assert.assertEquals(7, resultado.size());
		for (Map<String, Object> map : resultado) {
			if (map.get("codcat") != null) {
				if (map.get("codcat").equals("C18")) {
					Assert.assertEquals("C18", map.get("codcat"));
					Assert.assertEquals("Neoplasia maligna do cólon", map.get("nome"));
					Assert.assertEquals("C18   Neopl malig do colon", map.get("nome50"));
				}
			} else if (map.get("codsubcat") != null) {
				if (!map.get("codsubcat").toString().contains("C18")) {
					Assert.fail();
				}
			}
		}
	}

}