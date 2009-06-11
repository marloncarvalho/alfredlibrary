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
package net.marloncarvalho.alfred.cpf;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.numeros.Numeros;
import net.marloncarvalho.alfred.texto.Texto;

/**
 * Utilitários para CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class CPF {

	/**
	 * Construtor privado para evitar instanciação desta classe.
	 */
	private CPF() {
	}

	/**
     * Gerar um CPF arbitrário.
 	 * Código obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos são do autor do código.
	 *  
     * @return CPF gerado arbitrariamente.
     */
    public static String gerar() {
        StringBuilder iniciais = new StringBuilder();
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = Integer.valueOf((int) (Math.random() * 10));
            iniciais.append(numero.toString());
        }
        return iniciais.toString() + calcDigVerif(iniciais.toString());
    }

	/**
	 * Obter um CPF qualquer e formatá-lo. Qualquer caracter diferente de
	 * números será ignorado. Portanto, um CPF do tipo 1111c11b11122a será
	 * formatado para 111.111.111-11
	 * 
	 * @param cpf Número do CPF.
	 * @return CPF formatado.
	 */
	public static String formatar(String cpf) {
		String cpfSoNumeros = limpar(cpf);
		// Verificar tamanho do CPF.
		if (cpfSoNumeros.length() != 11)
			throw new AlfredException(
					"CPF inválido. Tamanho de um CPF válido é 11. Este CPF possui "
							+ cpfSoNumeros.length() + " números.");
		StringBuilder sb = new StringBuilder();
		sb.append(cpfSoNumeros.substring(0, 3));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(3, 6));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(6, 9));
		sb.append("-");
		sb.append(cpfSoNumeros.substring(9, 11));
		return sb.toString();
	}

	/**
	 * Verificar se um CPF é válido.
	 * 
	 * @param cpf CPF a ser verificado.
	 * @return Verdadeiro caso seja válido. Falso, caso contrário.
	 */
	public static boolean isValido(String cpf) {
		cpf = Texto.manterNumeros(cpf);
        if (cpf.length() != 11)
            return false;
        String numDig = cpf.substring(0, 9);
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));
	}

	/**
	 * Limpar o CPF mantendo somente os números. Não verifica se é um CPF
	 * válido.
	 * 
	 * @param cpf CPF que deve ser limpado.
	 * @return CPF apenas com números.
	 */
	public static String limpar(String cpf) {
		if (cpf == null)
			throw new AlfredException("O CPF informado é nulo.");
		if ("".equals(cpf))
			throw new AlfredException("O CPF informado é vazio.");
		char[] chars = cpf.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}

	/**
	 * Método que calcula o dígito verificador, observando se está correto.
	 * Código obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos são do autor do código.
	 * 
	 * @param num
	 * @return Dígito verificador.
	 */
	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

}