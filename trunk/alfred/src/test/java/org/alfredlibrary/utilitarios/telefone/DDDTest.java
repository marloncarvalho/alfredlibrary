/**
 * 
 */
package org.alfredlibrary.utilitarios.telefone;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.alfredlibrary.utilitarios.enums.EstadoBrasileiro;
import org.junit.Test;

/**
 * @author Mario Jorge Pereira
 * @since 31/12/2010
 *
 */
public class DDDTest {

	/**
	 * Test method for {@link org.alfredlibrary.utilitarios.telefone.DDD#obter(org.alfredlibrary.utilitarios.enums.EstadoBrasileiro, java.lang.String)}.
	 */
	@Test
	public final void testObterEstadoBrasileiroString() {
		String[] rsp = DDD.obter(EstadoBrasileiro.BAHIA, "salvador");
		Assert.assertEquals("71", rsp[0]);
	}

	/**
	 * Test method for {@link org.alfredlibrary.utilitarios.telefone.DDD#obter(java.lang.Integer)}.
	 */
	@Test
	public final void testObterInteger() {
	}

}
