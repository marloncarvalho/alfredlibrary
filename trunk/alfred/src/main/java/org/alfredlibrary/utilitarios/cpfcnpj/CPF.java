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
package org.alfredlibrary.utilitarios.cpfcnpj;

/**
 * Utilitários de CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
final public class CPF {

	private CPF() {}
	
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
        return iniciais.toString() + gerarDigitoVerificador(iniciais.toString());
    }
    
	/**
	 * Método que calcula o dígito verificador, observando se está correto.
	 * Código obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos são do autor do código.
	 * 
	 * @param num
	 * @return Dígito verificador.
	 */
	public static String gerarDigitoVerificador(String num) {
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
