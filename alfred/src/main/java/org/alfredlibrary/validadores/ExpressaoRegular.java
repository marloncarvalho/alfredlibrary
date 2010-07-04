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

package org.alfredlibrary.validadores;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador com Expressões Regulares.
 * 
 * Use a expressão regular e o valor
 * 
 * Constantes e formatos
 * 
 * DATASIMPLES         - Data (dd/mm/aaaa)         - 27/06/2010
 * DECIMAL             - Numero decimal            - 234.342
 * DOCUMENTO           - *.pdf|doc|txt|csv         - world.doc
 * EMAIL               - email                     - teste@email.com.br
 * COR_HTML	           - Codigo de Cor HTML        - #00ccff
 * IMAGEM              - *.jpg|gif|png             - alfred.jpg
 * IP                  - Endereço IP               - 192.168.0.1
 * MULTIMIDIA          - *.swf|mov|wma|mpg|mp3|wav - audio.wav
 * DATA_MYSQL		   - Data (aaaa-mm-dd)         - 2005-09-02	
 * TELEFONE_BR         - DDD + NUMERO              - (11) 5555-1977
 * CODIGO_POSTAL_US    - ^([A-Z][0-9]){3}$         - V2B2S3
 * HORA                - Hora (HH:MM)              - 12:29	
 * URL                 - endereço WWW              - http://www.google.com
 * 
 * @author Mario Jorge Pereira
 * @since 27/06/2010
 */
public class ExpressaoRegular {

    

    public static final String DATASIMPLES =  "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)";

    public static final String DECIMAL = "^\\d*[0-9](\\.\\d*[0-9])?$";
    
    public static final String DOCUMENTOS = "^[a-zA-Z0-9-_ \\.]+\\.(pdf|txt|doc|csv)$";
    
    //public static final String EMAIL = "^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$";
    public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[a-z]{1,3}\\.[a-z]{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
    
    public static final String COR_HTML = "^#?([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?$";
    
    public static final String IMAGEM = "^[a-zA-Z0-9-_ \\.]+\\.(jpg|gif|png)$";
    
    public static final String IP = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    
    public static final String MULTIMIDIA = "^[a-zA-Z0-9-_ \\.]+\\.(swf|mov|wma|mpg|mp3|wav)$";
      
    public static final String DATA_MYSQL = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    
    public static final String TELEFONE_BR = "^\\(?\\d{2}\\)?[\\s-]?\\d{4}-?\\d{4}$";
    
    public static final String CODIGO_POSTAL_US = "^([A-Z][0-9]){3}$";
    
    public static final String HORA = "^([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2}$";
    
    public static final String URL = "^(http[s]?://|ftp://)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)$";
    

	/**
	 * Verificar se valor é compativel no formato requerido.
	 *
	 * @param valor texto a ser validado.
         * @param expressao expressão regular no formato requerido.
	 * @return Verdadeiro caso seja valido. Falso, caso contrario.
	 */
	public static boolean isValido(String valor,String expressao) {
		Pattern pattern = Pattern.compile(expressao);
		Matcher m = pattern.matcher(valor);
		return m.matches();
	}

}
