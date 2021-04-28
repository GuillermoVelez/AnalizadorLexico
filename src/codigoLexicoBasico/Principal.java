
package codigoLexicoBasico;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
    public static void main(String[] args) throws Exception {
        String rutaFlex = "../AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/LexicoBasico.flex";
        String rutaCup = "../AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/LexicoBasicoCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/Sintax.cup"};
        generarLexicoBasico(rutaFlex, rutaCup, rutaS);
    }
    public static void generarLexicoBasico(String rutaFlex, String rutaCup, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(rutaFlex);
        JFlex.Main.generate(archivo);
        archivo = new File(rutaCup);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/sym.java"), 
                Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/sym.java")
        );
        
        Path rutaSin = Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/Sintax.java"), 
                Paths.get("C:/Users/USUARIO/Documents/U-V/2021-1/Ciencias III/AnalizadorLexicoBasicoV2/src/codigoLexicoBasico/Sintax.java")
        );
    }
}
