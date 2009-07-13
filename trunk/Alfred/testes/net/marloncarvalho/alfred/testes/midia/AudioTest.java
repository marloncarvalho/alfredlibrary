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
package net.marloncarvalho.alfred.testes.midia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import junit.framework.Assert;
import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.midia.Audio;

import org.junit.Test;

/**
 * Classe de teste para o utilitário de Áudio.
 * 
 * @author Marlon Silva Carvalho
 * @Since 13/07/2009
 */
public class AudioTest {

	@Test
	public void testarExecucao() {
		InputStream is;
		try {
			String userDir = System.getProperty("user.dir");
			is = new FileInputStream(userDir + "\\testes\\net\\marloncarvalho\\alfred\\testes\\midia\\love.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(is);
			Audio.executar(ais);
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (UnsupportedAudioFileException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} catch (AlfredException ae) {
			Assert.fail(ae.getMessage());
		}
	}

}