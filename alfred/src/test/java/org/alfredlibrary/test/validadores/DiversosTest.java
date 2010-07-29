package org.alfredlibrary.test.validadores;

import org.alfredlibrary.validadores.Diversos;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Validadores com Expressão Regular.
 * 
 * @author Mario Jorge Pereira
 * @since 04/07/2010
 */
public class DiversosTest {
	
	@Test
	public void testIsValido() {
		
		Assert.assertTrue(Diversos.isValido("27/06/2010",Diversos.DATASIMPLES));
		Assert.assertTrue(Diversos.isValido("2005-09-02",Diversos.DATA_MYSQL));
		Assert.assertTrue(Diversos.isValido("12:29",Diversos.HORA));
		Assert.assertTrue(Diversos.isValido("234.342",Diversos.DECIMAL));
		Assert.assertTrue(Diversos.isValido("world.doc",Diversos.DOCUMENTOS));
		Assert.assertTrue(Diversos.isValido("alfred.jpg",Diversos.IMAGEM));
		Assert.assertTrue(Diversos.isValido("audio.wav",Diversos.MULTIMIDIA));		
		Assert.assertTrue(Diversos.isValido("teste@email.com.br",Diversos.EMAIL));
		Assert.assertTrue(Diversos.isValido("marlon.carvalho@gmail.com",Diversos.EMAIL));
		Assert.assertTrue(Diversos.isValido("alonso@ferrari.com",Diversos.EMAIL));
		Assert.assertTrue(Diversos.isValido("marlon@silvacarvalho.net",Diversos.EMAIL));
		Assert.assertTrue(Diversos.isValido("alfred@alfredlibrary.org",Diversos.EMAIL));
		Assert.assertTrue(Diversos.isValido("http://www.google.com",Diversos.URL));
		Assert.assertTrue(Diversos.isValido("#00ccff",Diversos.COR_HTML));
		Assert.assertTrue(Diversos.isValido("192.168.0.1",Diversos.IP));
		Assert.assertTrue(Diversos.isValido("(11) 5555-1977",Diversos.TELEFONE_BR));
		Assert.assertTrue(Diversos.isValido("V2B2S3",Diversos.CODIGO_POSTAL_US));
		
	}
	
	@Test
	public void testIsInvalido() {
		
		Assert.assertFalse(Diversos.isValido("marlon=@t.com",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlon",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlon.carvalhogmail.com",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlon@g@g.com",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlong@g....com",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlon@.com",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("marlon@ccom",Diversos.EMAIL));
		Assert.assertFalse(Diversos.isValido("asf@1.com",Diversos.EMAIL));
	}
	

	

}
