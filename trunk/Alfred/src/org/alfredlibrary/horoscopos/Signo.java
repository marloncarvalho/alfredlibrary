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
package org.alfredlibrary.horoscopos;

/**
 * Enumaração com os Signos.
 *  
 * @author Marlon Silva Carvalho
 * @since 02/05/2010
 */
public enum Signo {
	ARIES("aries"), 
	TOURO("touro"), 
	LEAO("leao"), 
	VIRGEM("virgem"), 
	GEMEOS("gemeos"), 
	CANCER("cancer"), 
	LIBRA("libra"), 
	ESCORPIAO("escorpiao"), 
	SAGITARIO("sagitario"), 
	CAPRICORNIO("capricornio"), 
	PEIXES("peixes"), 
	AQUARIO("aquario");
	
	private final String signo;

	private Signo(final String signo) {
		this.signo = signo;
	}

	@Override
	public String toString() {
		return signo;
	}

}