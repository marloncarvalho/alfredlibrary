package org.alfredlibrary.test.validadores;

import org.alfredlibrary.validadores.Email;
import org.alfredlibrary.validadores.ExpressaoRegular;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Validadores com Expressão Regular.
 * 
 * @author Mario Jorge Pereira
 * @since 04/07/2010
 */
public class ExpressaoRegularTest {
	
	@Test
	public void testIsValido() {
		
		Assert.assertTrue(ExpressaoRegular.isValido("27/06/2010",ExpressaoRegular.DATASIMPLES));
		Assert.assertTrue(ExpressaoRegular.isValido("2005-09-02",ExpressaoRegular.DATA_MYSQL));
		Assert.assertTrue(ExpressaoRegular.isValido("12:29",ExpressaoRegular.HORA));
		Assert.assertTrue(ExpressaoRegular.isValido("234.342",ExpressaoRegular.DECIMAL));
		Assert.assertTrue(ExpressaoRegular.isValido("world.doc",ExpressaoRegular.DOCUMENTOS));
		Assert.assertTrue(ExpressaoRegular.isValido("alfred.jpg",ExpressaoRegular.IMAGEM));
		Assert.assertTrue(ExpressaoRegular.isValido("audio.wav",ExpressaoRegular.MULTIMIDIA));		
		Assert.assertTrue(ExpressaoRegular.isValido("teste@email.com.br",ExpressaoRegular.EMAIL));
		Assert.assertTrue(ExpressaoRegular.isValido("marlon.carvalho@gmail.com",ExpressaoRegular.EMAIL));
		Assert.assertTrue(ExpressaoRegular.isValido("alonso@ferrari.com",ExpressaoRegular.EMAIL));
		Assert.assertTrue(ExpressaoRegular.isValido("marlon@silvacarvalho.net",ExpressaoRegular.EMAIL));
		Assert.assertTrue(ExpressaoRegular.isValido("alfred@alfredlibrary.org",ExpressaoRegular.EMAIL));
		Assert.assertTrue(ExpressaoRegular.isValido("http://www.google.com",ExpressaoRegular.URL));
		Assert.assertTrue(ExpressaoRegular.isValido("#00ccff",ExpressaoRegular.COR_HTML));
		Assert.assertTrue(ExpressaoRegular.isValido("192.168.0.1",ExpressaoRegular.IP));
		Assert.assertTrue(ExpressaoRegular.isValido("(11) 5555-1977",ExpressaoRegular.TELEFONE_BR));
		Assert.assertTrue(ExpressaoRegular.isValido("V2B2S3",ExpressaoRegular.CODIGO_POSTAL_US));
		
	}
	
	@Test
	public void testIsInvalido() {
		
		Assert.assertFalse(ExpressaoRegular.isValido("marlon=@t.com",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlon",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlon.carvalhogmail.com",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlon@g@g.com",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlong@g....com",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlon@.com",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("marlon@ccom",ExpressaoRegular.EMAIL));
		Assert.assertFalse(ExpressaoRegular.isValido("asf@1.com",ExpressaoRegular.EMAIL));
	}
	

	

}
