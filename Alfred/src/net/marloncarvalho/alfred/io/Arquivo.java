/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.io;


import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

/**
 * Utilitarios para Arquivos
 *
 * @author Mario Jorge Pereira
 * @since 09/06/2009
 */
public class Arquivo {

    public static boolean salvar(String local, byte[] fileData, String nome) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        File f = new File(local + nome);
        if (f.exists()) {
            return false;
        } else {
            out = new FileOutputStream(f);
            out.write(fileData);
            out.flush();
            out.close();
        }
        return true;
    }

    public static boolean sobrescrever(String local, byte[] fileData, String nome) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        File f = new File(local + nome);
        if (f.exists()) {
            f.delete();
        }
        out = new FileOutputStream(f);
        out.write(fileData);
        out.flush();
        out.close();
        return true;
    }

    public static File[] listarArquivosDiretorio(String local) {
        File f = new File(local);
        File[] arquivos = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String arquivo = pathname.getName().toLowerCase();
                return ((arquivo.endsWith(".gif") || (arquivo.endsWith(".jpg")) || (arquivo.endsWith(".jpeg")) || (arquivo.endsWith(".png")) || (arquivo.endsWith(".bmp")) ));
            }
        });
        return arquivos;
    }

    public static File[] listFilesAsArray(File directory, FilenameFilter filter, boolean recurse) {
        Collection<File> files = listFiles(directory, filter, recurse);
        File[] arr = new File[files.size()];
        return files.toArray(arr);
    }
    
    public static Collection<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
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
    
    public static String extensao(File file) {
        String resp = "";
        String nome = file.getName();
        int i = nome.lastIndexOf(".");
        if (i != -1) {
            resp = nome.substring(i);
        }
        return resp;
    }

    public static String tipo(String nome) {
        String resp = "";
        int i = nome.lastIndexOf(".");
        if (i != -1) {
            resp = nome.substring(i+1);
        }
        return resp.toUpperCase();
    }
    
}
