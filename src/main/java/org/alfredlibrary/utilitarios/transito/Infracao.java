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
package org.alfredlibrary.utilitarios.transito;

import java.io.Serializable;

/**
 * Classe representando uma Infração de Trânsito.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/05/2010
 */
public class Infracao implements Serializable {
	private static final long serialVersionUID = -1763492785388989062L;
	private String codigo;
	private String descricao;
	private String amparoLegal;
	private String infrator;
	private Integer pontos;
	private Double valor;
	private GravidadeInfracao gravidade;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getAmparoLegal() {
		return amparoLegal;
	}
	
	public void setAmparoLegal(String amparoLegal) {
		this.amparoLegal = amparoLegal;
	}
	
	public String getInfrator() {
		return infrator;
	}
	
	public void setInfrator(String infrator) {
		this.infrator = infrator;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
	public void setPontos(Integer pontos) {
		switch(pontos) {
			case 7:
				setGravidade(GravidadeInfracao.Gravissima);
				break;
			case 5:
				setGravidade(GravidadeInfracao.Grave);
				break;
			case 4:
				setGravidade(GravidadeInfracao.Media);
				break;
			case 3:
				setGravidade(GravidadeInfracao.Leve);
				break;
		}
		this.pontos = pontos;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setGravidade(GravidadeInfracao gravidade) {
		this.gravidade = gravidade;
	}

	public GravidadeInfracao getGravidade() {
		return gravidade;
	}
	
}