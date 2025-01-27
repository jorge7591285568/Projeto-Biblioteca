
package br.edu.ifba.biblioteca.util;

import java.io.*;

public class FileUtil {
    private static final String DATA_DIR = "resources/data/";

    static {
        new File(DATA_DIR).mkdirs();
    }

    public static void salvarObjeto(Object obj, String nomeArquivo) throws IOException {
        String caminhoCompleto = DATA_DIR + nomeArquivo;
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(caminhoCompleto))) {
            oos.writeObject(obj);
        }
    }

    public static Object carregarObjeto(String nomeArquivo) throws IOException, ClassNotFoundException {
        String caminhoCompleto = DATA_DIR + nomeArquivo;
        File arquivo = new File(caminhoCompleto);
        if (!arquivo.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(caminhoCompleto))) {
            return ois.readObject();
        }
    }
}
