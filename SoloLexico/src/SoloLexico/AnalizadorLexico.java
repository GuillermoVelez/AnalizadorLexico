package SoloLexico;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author mdelgado
 */
public class AnalizadorLexico {
    public static void main(String[] args) throws Exception {
        String caminoJFLEX= "../SoloLexico/src/SoloLexico/Lexico.flex";
        String caminoJCUP= "../SoloLexico/src/SoloLexico/LexicoCup.flex";
        String[] caminoSCUP= {"-parser","Sintax","../SoloLexico/src/SoloLexico/Sintax.cup"};
        generarLex(caminoJFLEX, caminoJCUP, caminoSCUP);
    }
    public static void generarLex(String caminoJFLEX, String caminoJCUP, String[] caminoSCUP) throws IOException, Exception {
        File arc; 
        arc = new File(caminoJFLEX);
        JFlex.Main.generate(arc);
        arc = new File(caminoJCUP);
        
        JFlex.Main.generate(arc);
        java_cup.Main.main(caminoSCUP);     
        
        Path caminoSym = Paths.get("../SoloLexico/src/SoloLexico/sym.java");
        if (Files.exists(caminoSym)) {
            Files.delete(caminoSym);
        }
        Files.move(
                Paths.get("../SoloLexico/sym.java"), 
                Paths.get("../SoloLexico/src/SoloLexico/sym.java")
        );
        Path caminoSint = Paths.get("../SoloLexico/src/SoloLexico/Sintax.java");
        if (Files.exists(caminoSint)) {
            Files.delete(caminoSint);
        }
        Files.move(
                Paths.get("../SoloLexico/Sintax.java"), 
                Paths.get("../SoloLexico/src/SoloLexico/Sintax.java")
        );       
    }
}
