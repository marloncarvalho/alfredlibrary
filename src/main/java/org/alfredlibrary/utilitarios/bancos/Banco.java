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
package org.alfredlibrary.utilitarios.bancos;

/**
 * Bean que representa um Banco.
 * 
 * @author Marlon Silva Carvalho
 * @since 05/05/2010
 */
final public class Banco {
	private String codigo;
	private String nome;
	private String site;
	private Integer quantidadeAgencias;
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSite() {
		return site;
	}

	public void setQuantidadeAgencias(Integer quantidadeAgencias) {
		this.quantidadeAgencias = quantidadeAgencias;
	}

	public Integer getQuantidadeAgencias() {
		return quantidadeAgencias;
	}
	
}