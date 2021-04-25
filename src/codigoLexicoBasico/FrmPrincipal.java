package codigoLexicoBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java_cup.runtime.Symbol;

public class FrmPrincipal extends javax.swing.JFrame {

    File archivo;
    
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtResultado.setEditable(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        codresultado = new javax.swing.JTextArea();
        leer = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        codresultado.setColumns(20);
        codresultado.setRows(5);
        jScrollPane2.setViewportView(codresultado);

        leer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        leer.setText("Leer");
        leer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerActionPerformed(evt);
            }
        });

        Guardar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leer, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        // TODO add your handling code here:
        File Temporal = new File("Temporal.txt");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(Temporal);
            escribir.print(codresultado.getText());
            escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int cont =1;
            Reader lector;
            lector = new BufferedReader(new FileReader("Temporal.txt")); 
            LexicoBasico lexicobasico = new LexicoBasico(lector);
            //Preguntar: LexicoBasicoCup lexicobasicocup = new LexicoBasicoCup(lector);//
            String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";
            
            while (true) {
                Tokens tokens = lexicobasico.yylex();
                
                if (tokens == null) {
                    resultado += "FIN";
                    txtResultado.setText(resultado);
                    return;
                }
                
                switch (tokens) {
                    case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    break;
                case Comillas:
                    resultado += "  <Comillas>\t\t" + lexicobasico.lexemas + "\n";
                    break;
                case Cadena:
                    resultado += "  <Tipo de dato>\t" + lexicobasico.lexemas + "\n";
                    break;
                case T_dato:
                    resultado += "  <Tipo de dato>\t" + lexicobasico.lexemas + "\n";
                    break;
                case If:
                    resultado += "  <Reservada if>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Else:
                    resultado += "  <Reservada else>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Do:
                    resultado += "  <Reservada do>\t" + lexicobasico.lexemas + "\n";
                    break;
                case While:
                    resultado += "  <Reservada while>\t" + lexicobasico.lexemas + "\n";
                    break;
                case For:
                    resultado += "  <Reservada while>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Igual:
                    resultado += "  <Operador igual>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Suma:
                    resultado += "  <Operador suma>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Resta:
                    resultado += "  <Operador resta>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Multiplicacion:
                    resultado += "  <Operador multiplicacion>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Division:
                    resultado += "  <Operador division>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Op_logico:
                    resultado += "  <Operador logico>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Op_incremento:
                    resultado += "  <Operador incremento>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Op_relacional:
                    resultado += "  <Operador relacional>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Op_atribucion:
                    resultado += "  <Operador atribucion>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Op_booleano:
                    resultado += "  <Operador booleano>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Parentesis_a:
                    resultado += "  <Parentesis de apertura>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Parentesis_c:
                    resultado += "  <Parentesis de cierre>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Llave_a:
                    resultado += "  <Llave de apertura>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Llave_c:
                    resultado += "  <Llave de cierre>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Corchete_a:
                    resultado += "  <Corchete de apertura>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Corchete_c:
                    resultado += "  <Corchete de cierre>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Main:
                    resultado += "  <Reservada main>\t" + lexicobasico.lexemas + "\n";
                    break;
                case P_coma:
                    resultado += "  <Punto y coma>\t" + lexicobasico.lexemas + "\n";
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t\t" + lexicobasico.lexemas + "\n";
                    break;
                case Numero:
                    resultado += "  <Numero>\t\t" + lexicobasico.lexemas + "\n";
                    break;
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    break;
                default:
                    resultado += "  < " + lexicobasico.lexemas + " >\n";
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void leerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerActionPerformed
        String aux = "";
        String texto = "";
        try {
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(this);
            archivo = jf.getSelectedFile();
            /*  if (archivo != null) {
            codresultado.setText(archivo.getAbsolutePath());
        }*/

            if (archivo != null) {
                FileReader archivos = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux = lee.readLine()) != null) {
                    texto += aux + "\n";
                }
                lee.close();
            }
            codresultado.setText(texto);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            // TODO add your handling code here:
    }//GEN-LAST:event_leerActionPerformed
    }
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        System.out.println("hola");
                String contenido = codresultado.getText();
                String respuesta = GuardarATexto(archivo, contenido);
                if(respuesta!=null){
                    JOptionPane.showMessageDialog(null, respuesta);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar texto.");
                }
        
    }//GEN-LAST:event_GuardarActionPerformed
     public String GuardarATexto(File archivo, String contenido){
        FileOutputStream salida;
        String respuesta=null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se guardo con exito el archivo";
        } catch (Exception e) {
        }
        return respuesta;
    }
 
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JTextArea codresultado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton leer;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
