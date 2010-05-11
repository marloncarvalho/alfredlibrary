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
package org.alfredlibrary.utilitarios.clima;

import java.util.Date;

/**
 * Informa��es do Clima.
 * 
 * @author Marlon Silva Carvalho
 * @since 26/05/2010
 */
public class Clima {
	private Date data;
	private Integer maxima;
	private Integer minima;
	private String frase;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getMaxima() {
		return maxima;
	}

	public void setMaxima(Integer maxima) {
		this.maxima = maxima;
	}

	public Integer getMinima() {
		return minima;
	}

	public void setMinima(Integer minima) {
		this.minima = minima;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

}