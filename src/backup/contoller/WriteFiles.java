/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.contoller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static wiew.Main.getProp;
public class WriteFiles {
    
    static  Properties prop;
      static final String path=prop.getProperty("prop.server.arquivolog");
	public static void main(String[] args) throws IOException {
		File file = new File(path);
		long begin = System.currentTimeMillis();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("Arquivo gravado em : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
		writer.newLine();
		writer.write("Caminho da gravação: " + path);
		writer.newLine();
		long end = System.currentTimeMillis();
		writer.write("Tempo de gravação: " + (end - begin) + "ms.");
		//Criando o conteúdo do arquivo
		writer.flush();
		//Fechando conexão e escrita do arquivo.
		writer.close();
		System.out.println("Arquivo gravado em: " + path);
	}
             public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\ERPSaude/Backup/dados.properties");
		props.load(file);
		return props;

	}

    public WriteFiles() {
          try {
              this.prop = getProp();
          } catch (IOException ex) {
              Logger.getLogger(WriteFiles.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}