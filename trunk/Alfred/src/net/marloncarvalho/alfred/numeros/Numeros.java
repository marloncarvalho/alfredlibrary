package net.marloncarvalho.alfred.numeros;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Utilitários para Números.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class Numeros {

	/**
	 * Verificar se o número da String é um Número.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja Número. Falso, caso contrário.
	 */
	public static boolean isNumber(String numero) {
		try {
			return isBigDecimal(numero);
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um Short.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja Short. Falso, caso contrário.
	 */
	public static boolean isShort(String numero) {
		try {
			Short.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um inteiro.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja inteiro. Falso, caso contrário.
	 */
	public static boolean isInteger(String numero) {
		try {
			Long.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um Double.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja Double. Falso, caso contrário.
	 */
	public static boolean isDouble(String numero) {
		try {
			Double.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um Float.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja Float. Falso, caso contrário.
	 */
	public static boolean isFloat(String numero) {
		try {
			Float.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um BigDecimal.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja BigDecimal. Falso, caso contrário.
	 */
	public static boolean isBigDecimal(String numero) {
		try {
			new BigDecimal(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o número da String é um Long.
	 * 
	 * @param numero Número.
	 * @return Verdadeiro caso seja Long. Falso, caso contrário.
	 */
	public static boolean isLong(String numero) {
		try {
			Long.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Obter uma String contendo os números primos até 'numero' seperados por espaço.
	 * 
	 * @param numero 
	 * @return Números primos separados por espaço.
	 */
	public static String obterNumerosPrimosAte(int numero) {
		StringBuilder sb = new StringBuilder();
		for(int i=2;i<=numero;i++) {
			BigInteger bigInteger = BigInteger.valueOf(i);
			if ( bigInteger.isProbablePrime(100) ) {
				sb.append(bigInteger);
				sb.append(" ");
			}
		}
		return sb.toString().trim();
	}

}