/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.conversores;

import net.marloncarvalho.alfred.AlfredException;

/**
 * Utilitário para conversão entre bases numéricas.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class BaseNumerica {

	/**
	 * Converter um número Decimal para Binário. 
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param decimal Número decimal.
	 * @return Número em base binária.
	 */
	public static String converterDecimalEmBinario(String decimal) {
		int i = 0, aux[], resto, n;
		try {
			n = Integer.valueOf(decimal);
		} catch (RuntimeException re) {
			throw new AlfredException(re);
		}
		String saida = "";
		aux = new int[64];
		saida += "1";
		do {
			resto = n % 2;
			aux[i] = resto;
			n /= 2;
			++i;
		} while (n >= 2);
		do
			saida += Integer.toString(aux[i - 1]);
		while (--i >= 1);
		return saida;
	}

	/**
	 * Converter um número Binário para Decimal.
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param binario Número binário.
	 * @return Número em base decimal.
	 */
	public static String converterBinarioEmDecimal(String binario) {
        int i = 0,  j = 0;
        int aux[] = new int [ 32 ];
        int n = 0;
        for ( i = 0; i < binario.length(); i++  )
            aux [ i ] = (int) binario.charAt( i ) - 48;
        do {
            n += aux [ j ] * Math.pow( 2, ( i - 1 ) );
            --i;
            ++j;
        }
        while ( i >= 1 );
        return binario;
	}

	/**
	 * Converter um número Decimal para Hexadecimal.
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param binario Número decimal.
	 * @return Número em base hexadecimal.
	 */
	public static String converterDecimalEmHexadecimal(String decimal) {
		int i = 0, aux[], resto, n;
		try {
			n = Integer.valueOf(decimal);
		} catch (RuntimeException re) {
			throw new AlfredException(re);
		}
		String saida = "";
		aux = new int[32];
		do {
			resto = n % 16;
			aux[i] = resto;
			n /= 16;
			++i;
		} while (n >= 16);
		if (n < 10)
			saida += Integer.toString(n);
		else if (n == 10)
			saida += "A";
		else if (n == 11)
			saida += "B";
		else if (n == 12)
			saida += "C";
		else if (n == 13)
			saida += "D";
		else if (n == 14)
			saida += "E";
		else if (n == 15)
			saida += "F";
		do {
			if (aux[i - 1] < 10)
				saida += Integer.toString(aux[i - 1]);
			else if (aux[i - 1] == 10)
				saida += "A";
			else if (aux[i - 1] == 11)
				saida += "B";
			else if (aux[i - 1] == 12)
				saida += "C";
			else if (aux[i - 1] == 13)
				saida += "D";
			else if (aux[i - 1] == 14)
				saida += "E";
			else if (aux[i - 1] == 15)
				saida += "F";
		} while (--i >= 1);
		return saida;
	}

	/**
	 * Converter um número Hexadecimal para Decimal.
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param binario Número hexadecimal.
	 * @return Número em base decimal.
	 */
	public static String converterHexadecimalEmDecimal(String hexadecimal) {
        int i = 0,  j = 0;
        int aux[] = new int [ 32 ];
        int n = 0;
        for ( i = 0; i < hexadecimal.length(); i++  )
            if ( hexadecimal.charAt( i ) == (char) 65  || hexadecimal.charAt( i ) == (char) 97 )
                aux [ i ] = 10;
            else if ( hexadecimal.charAt( i ) == (char) 66  || hexadecimal.charAt( i ) == (char) 98 )
                aux [ i ] = 11;
            else if ( hexadecimal.charAt( i ) == (char) 67  || hexadecimal.charAt( i ) == (char) 99 )
                aux [ i ] = 12;
            else if ( hexadecimal.charAt( i ) == (char) 68  || hexadecimal.charAt( i ) == (char) 100 )
                aux [ i ] = 13;
            else if ( hexadecimal.charAt( i ) == (char) 69  || hexadecimal.charAt( i ) == (char) 101 )
                aux [ i ] = 14;
            else if ( hexadecimal.charAt( i ) == (char) 70  || hexadecimal.charAt( i ) == (char) 102 )
                aux [ i ] = 15;
            else
                aux [ i ] = (int) hexadecimal.charAt( i ) - 48;
        do {
            n += aux [ j ] * Math.pow( 16, ( i - 1 ) );
            --i;
            ++j;
        }
        while ( i >= 1 );
        return  Integer.toString(n);
	}

	/**
	 * Converter um número Decimal para Octa.
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param binario Número decimal.
	 * @return Número em base octa.
	 */
	public static String converterDecimalEmOcta(String decimal) {
		String saida = "";
		int i = 0, aux[], resto, n;
		try {
			n = Integer.valueOf(decimal);
		} catch (RuntimeException re) {
			throw new AlfredException(re);
		}
		aux = new int[32];
		do {
			resto = n % 8;
			aux[i] = resto;
			n /= 8;
			i++;
		} while (n >= 8);
		saida += Integer.toString(n);
		do
			saida += Integer.toString(aux[i - 1]);
		while (--i >= 1);
		return saida;
	}

	/**
	 * Converter um número Octa para Decimal.
	 * Código obtido do Linha de Código no endereço
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param binario Número octa.
	 * @return Número em base decimal.
	 */
	public static String converterOctaEmDecimal(String octa) {
        int i = 0,  j = 0;
        int aux[] = new int [ 32 ];
        int n = 0;
        for ( i = 0; i < octa.length(); i++ )
            aux [ i ] = (int) octa.charAt( i ) - 48;
        do {
            n += aux [ j ] * Math.pow( 8, ( i - 1 ) );
            --i;
            ++j;
        }
        while ( i >= 1 );
        return Integer.toString(n);
	}

}