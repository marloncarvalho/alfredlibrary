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

package org.alfredlibrary.conversores;

/**
 * Classe parâmetro para conversão de medidas compostas simétricas.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/05/2010
 */
public class UnidadeComposta {

	private Object unidade; // A unidade de medida (enum da classe base de conversão) 
	private int potencia; // Se a medida for elevada a alguma potência
	private boolean numerador; // Se a medida for numerador (TRUE) ou denominador (FALSE)
	
	public UnidadeComposta() {
		super();
		potencia = 1;
		numerador = true;
	}
	
	public UnidadeComposta(Object unidade) {
		this();
		this.unidade = unidade;
	}
	
	public UnidadeComposta(Object unidade, int potencia, boolean numerador) {
		super();
		this.unidade = unidade;
		this.potencia = (potencia != 0 ? potencia : 1);
		this.numerador = numerador;
	}
	
	public Object getUnidade() {
		return unidade;
	}
	public void setUnidade(Object unidade) {
		this.unidade = unidade;
	}
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	public boolean isNumerador() {
		return numerador;
	}
	public void setNumerador(boolean numerador) {
		this.numerador = numerador;
	}
	
}
