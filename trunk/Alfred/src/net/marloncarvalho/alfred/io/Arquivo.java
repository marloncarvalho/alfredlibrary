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
package net.marloncarvalho.alfred.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import net.marloncarvalho.alfred.AlfredException;

/**
 * Utilitarios para Arquivos
 * 
 * @author Mario Jorge Pereira
 * @since 09/06/2009
 */
public class Arquivo {

	/**
	 * 
	 * @param local
	 * @param fileData
	 * @param nome
	 * @return
	 */
	public static boolean salvar(String local, byte[] fileData, String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			return false;
		} else {
			try {
				out = new FileOutputStream(f);
				out.write(fileData);
				out.flush();
				out.close();
			} catch (FileNotFoundException ex) {
				throw new AlfredException("Arquivo não encontrado! ", ex);
			} catch (IOException ex) {
				throw new AlfredException("Erro ao gravar o arquivo! ", ex);
			}

		}
		return true;
	}

	public static boolean sobrescrever(String local, byte[] fileData,
			String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			f.delete();
		}
		try {
			out = new FileOutputStream(f);
			out.write(fileData);
			out.flush();
			out.close();
		} catch (FileNotFoundException ex) {
			throw new AlfredException("Arquivo não encontrado! ", ex);
		} catch (IOException ex) {
			throw new AlfredException("Erro ao gravar o arquivo! ", ex);
		}
		return true;
	}

	/**
	 * 
	 * @param local
	 * @return
	 */
	public static File[] listarArquivosEspecificosDiretorio(String local, final List<String> terminacoes) {
		File f = new File(local);
		File[] arquivos = f.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String arquivo = pathname.getName().toLowerCase();
				boolean resposta = false;
				for(String extensao : terminacoes){
					if(arquivo.endsWith(extensao.toLowerCase())){
						resposta=true;
					}
				}
				return resposta;
			}
		});
		return arquivos;
	}

	/**
	 * 
	 * @param directory
	 * @param filter
	 * @param recurse
	 * @return
	 */
	public static File[] listFilesAsArray(File directory,
			FilenameFilter filter, boolean recurse) {
		Collection<File> files = listFiles(directory, filter, recurse);
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	/**
	 * 
	 * @param directory
	 * @param filter
	 * @param recurse
	 * @return
	 */
	public static Collection<File> listFiles(File directory,FilenameFilter filter, boolean recurse) {
		Vector<File> files = new Vector<File>();
		File[] entries = directory.listFiles();
		for (File entry : entries) {
			if (filter == null || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}
			if (recurse && entry.isDirectory()) {
				files.addAll(listFiles(entry, filter, recurse));
			}
		}
		return files;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String extensao(File file) {
		String nome = file.getName();
		return extensao(nome);
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public static String extensao(String nome) {
		String resp = "";
		int i = nome.lastIndexOf(".");
		if (i != -1) {
			resp = nome.substring(i + 1);
		}
		return resp.toLowerCase();
	}

}
