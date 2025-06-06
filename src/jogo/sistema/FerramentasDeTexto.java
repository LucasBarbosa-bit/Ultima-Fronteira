package jogo.sistema;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FerramentasDeTexto {

    /**
     * Remove acentos e converte para minúsculas para facilitar comparações.
     * Ex: "Água" -> "agua"
     */
    public static String normalizar(String texto) {
        if (texto == null) {
            return null;
        }
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(textoNormalizado).replaceAll("").toLowerCase();
    }
}