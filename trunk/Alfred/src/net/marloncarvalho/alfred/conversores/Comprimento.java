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
package net.marloncarvalho.alfred.conversores;

/**
 * Classe para conversão entre medidas de comprimento.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Comprimento {
	public static double QUILOMETRO = 1D;
	public static double METRO = 1000D;
	public static double DECIMETRO  = 10000D;
	public static double CENTIMETRO = 100000D;
	public static double MILIMETRO = 1000000D;
	public static double MICROMETRO = 1000000000D;
	public static double NANOMETRO = 1000000000000D;
	public static double ANGSTROM = 10000000000000D;
	
	private Comprimento() {}
	
	/**
	 * Converter uma unidade de comprimento para outra.
	 *
	 * @param valor Valor a ser convertido.
	 * @param unidadeEntrada Unidade de Entrada.
	 * @param unidadeSaida Unidade de Saída.
	 * @return Valor convertido.
	 */
	public static double converter(double valor, double unidadeEntrada, double unidadeSaida) {
		if ( unidadeEntrada > unidadeSaida )
			return (valor/(unidadeEntrada/unidadeSaida));
		else return (valor*(unidadeSaida/unidadeEntrada));
	}
	
	/**
	 * Converter quilômetros em metros.
	 * 
	 * @param km Quilômetros.
	 * @return Metros.
	 */
	@Deprecated
	public static double converterKmEmMetros(double km) {
		return (km*1000);
	}

	/**
	 * Converter metros em quilômetros.
	 * @param m Metros.
	 * @return Km.
	 */
	@Deprecated
	public static double converterMetrosEmKm(double m) {
		return (m/1000);
	}

	/**
	 * Converter Milhas em Quilômetros.
	 * @param km Km.
	 * @return Milhas.
	 */
	public static double converterKmEmMilhas(double km) {
		return (km*0.6213711922);
	}

	/**
	 * Converter Milhas em Quilômetros.
	 * @param m Milhas.
	 * @return Km.
	 */
	public static double converterMilhasEmKm(double m) {
		return (m*1.609344);
	}

	/**
	 * Converter Jardas (Yard) em Metro.
	 * 
	 * @param j Jardas.
	 * @return Metro.
	 */
	public static double converterJardasEmMetro(double j) {
		return (j*0.914399);
	}

	/**
	 * Converter metro em jardas.
	 * 
	 * @param m Metro.
	 * @return Jardas
	 */
	public static double converterMetroEmJardas(double m) {
		return (m*1.093614);
	}
	
	/**
	 * Converter Polegada em Centímetro.
	 * 
	 * @param p Polegada.
	 * @return Centímetro.
	 */
	public static double converterPolegadaEmCentimentro(double p) {
		return (p*2.54);
	}
	
	/**
	 * Converter Centímetro em Polegada.
	 * 
	 * @param c Centímetro.
	 * @return Polegada.
	 */
	public static double converterCentimetroEmPolegada(double c) {
		return (c*0.3937007874);
	}

	/**
	 * Converter Ano Luz em Quilômetro.
	 * 
	 * @param al Ano Luz.
	 * @return Km.
	 */
	public static double converterAnoLuzEmKm(double al) {
		 return (al * 9.4607304726 * 10);
	}

	/**
	 * Converter Milha Marítima em Quilômetro.
	 * 
	 * @param mm Milha Marítima.
	 * @return Km.
	 */
	public static double converterMilhaMaritimaEmKm(double mm) {
		return (mm*1.852);
	}

	/**
	 * Converter Quilômetro em Milha Marítima.
	 * 
	 * @param km Km.
	 * @return Milha Marítima.
	 */
	public static double converterKmEmMilhaMaritima(double km) {
		return (km/1.852);
	}

	/**
	 * Converter Metro em Pés.
	 * @param m Metro.
	 * @return Pés.
	 */
	public static double converterMetroEmPes(double m) {
		return (m*3.280839895);
	}
	
	/**
	 * Converter Pés em Metro.
	 * @param p Pés.
	 * @return Metro.
	 */
	public static double converterPesEmMetro(double p) {
		return (p*0.3048);
	}

}