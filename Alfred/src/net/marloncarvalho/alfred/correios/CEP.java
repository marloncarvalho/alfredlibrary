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
package net.marloncarvalho.alfred.correios;

import java.util.Collection;
import java.util.Map;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.io.CSVReader;

/**
 * Classe utilitária para CEPs.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CEP {

	private CEP() {}

	/**
	 * Realizar a formatação do CEP passado.
	 * A formatação é do tipo XX.XXX-XXX.
	 * 
	 * @param cep CEP a ser formatado.
	 * @return CEP formatado.
	 */
	public static String formatar(String cep) {
		if ( "".equals(cep) || cep.length() != 8 )
			throw new AlfredException("Informe um CEP válido.");
		return new StringBuilder().append(cep.substring(0,2)).append(".").append(cep.substring(2,5)).append("-").append(cep.substring(5,8)).toString();
	}

	/**
	 * Realizar a formatação do CEP passado.
	 * A formatação é do tipo XXXXX-XXX.
	 * 
	 * @param cep CEP a ser formatado.
	 * @return CEP formatado.
	 */
	public static String formatarSimples(String cep) {
		if ( "".equals(cep) || cep.length() != 8 )
			throw new AlfredException("Informe um CEP válido.");
		return new StringBuilder().append(cep.substring(0,5)).append("-").append(cep.substring(5,8)).toString();
	}

	/**
	 * Consultar um Endereço pelo CEP.
	 * Será retornado um Array contendo 6 posições, que conterão, respectivamente, os campos (tipo de logradouro, logradouro, bairro, cidade, sigla do estado, estado).
	 * 
	 * @param cep CEP a ser consultado.
	 * @return Array contendo o resultado da consulta.
	 */
	public static String[] consultarEndereco(String cep) { 
		String cepFormatado = formatarSimples(cep);
		Collection<Map<String, String>> r = CSVReader.interpretar("http://ceplivre.pc2consultoria.com/index.php?module=cep&cep=" + cepFormatado + "&formato=csv");
		if ( r.size() <= 0 )
			throw new AlfredException("Endereço não encontrado.");
		Map<String, String> endereco = (Map<String, String>) r.iterator().next();
		if ( endereco == null )
			throw new AlfredException("Endereço não encontrado.");
		return new String[] {endereco.get("tipo_logradouro"),endereco.get("logradouro"), endereco.get("bairro"), endereco.get("cidade"),endereco.get("sigla"),endereco.get("estado")};
	}

}