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
package org.alfredlibrary.utilitarios.midia;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.alfredlibrary.AlfredException;

/**
 * Utilit�rios de �udio.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/06/2009
 */
final public class Audio {
	
	private Audio() {
		throw new AssertionError();
	}

	/**
	 * Executar um Arquivo de �udio.
	 * 
	 * @param audioInputStream Stream de �udio.
	 */
	public static void executar(AudioInputStream audioInputStream) {
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (LineUnavailableException e) {
			throw new AlfredException(e);
		} catch (Exception e) {
			throw new AlfredException(e);
		}
		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[524288];
		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			throw new AlfredException(e);
		} finally {
			try {
				audioInputStream.close();
			} catch (IOException e) {
				throw new AlfredException(e);
			}
			auline.drain();
			auline.close();
		}
	}

}