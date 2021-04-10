package codigoLexicoBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(leer, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            Reader lector;
            lector = new BufferedReader(new FileReader("Temporal.txt")); 
            LexicoBasico lexicobasico = new LexicoBasico(lector);
            String resultado = "";
            while (true) {
                Tokens tokens = lexicobasico.yylex();
                if (tokens == null) {
                    resultado += "FIN";
                    txtResultado.setText(resultado);
                    return;
                }
                switch (tokens) {
                    case ERROR:
                        resultado += "Simbolo no definido\n";
                        break;
                    case Identificador:
                    case Numero:
                    case Reservadas:
                        resultado += lexicobasico.lexemas + ": Es " + tokens + "\n";
                        break;
                    default:
                        resultado += "Token: " + tokens + "\n";
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JTextArea codresultado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton leer;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
