/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.contoller;

import backup.model.Backup;
import backup.model.ConfiguracaoBackp;
import backup.util.Compactador;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import wiew.Main;

/**
 *
 * @author Daniel
 */
public class BackupBean {
static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BackupBean.class.getName());
  File file;
  String aux;
  ArrayList<String> linhas = new ArrayList<String> ();
  
    public boolean backupDataWithOutDatabase(ConfiguracaoBackp conf) {
boolean status = false;
         file = new File(conf.getArquivoLog()); 
 Toolkit.getDefaultToolkit().beep();
log.info("Copia do banco: " + conf.getNomeBanco()+" iniciada.......................");
linhas.add("Copia do banco: " + conf.getNomeBanco()+" iniciada.......................");
log.info("Dados: "+conf.getLocalMysql()+conf.getHost()+conf.getPorta()+ conf.getUsuario()+  conf.getSenha()+ conf.getNomeBanco()+ conf.getLocalGravar());
linhas.add("Dados: "+conf.getLocalMysql()+conf.getHost()+conf.getPorta()+ conf.getUsuario()+  conf.getSenha()+ conf.getNomeBanco()+ conf.getLocalGravar());
try {
Process p = null;
 
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
Date date = new Date();
String filepath = "backup(without_DB)-" + conf.getNomeBanco() + "-" + conf.getHost() + "-(" + dateFormat.format(date) + ").sql";
String arquivo =conf.getNomeBanco() + dateFormat.format(date) + ".sql";


String batchCommand = "";
if (conf.getSenha()!= "") {
//only backup the data not included create database
batchCommand = conf.getLocalMysql() + " -h " + conf.getHost() + " --port " +conf.getPorta() + " -u " + conf.getUsuario() + " --password=" + conf.getSenha()+ " " + conf.getNomeBanco() + " -r \"" + conf.getLocalGravar() + "" + filepath + "\"";
} else {
batchCommand = conf.getLocalMysql() + " -h " + conf.getHost()+ " --port " + conf.getPorta() + " -u " + conf.getUsuario()+ " " + conf.getNomeBanco() + " -r \"" + conf.getLocalGravar() + "" + arquivo + "\"";
}


Runtime runtime = Runtime.getRuntime();
p = runtime.exec(batchCommand);
int processComplete = p.waitFor();

 
if (processComplete == 0) {
status = true;
log.info("Copia criada com sucesso " + conf.getNomeBanco() + " in " + conf.getHost() + ":" + conf.getPorta());
linhas.add("Copia criada com sucesso " + conf.getNomeBanco() + " in " + conf.getHost() + ":" + conf.getPorta());

            
Compactador c = new Compactador();

log.info("Inicia a compctação.......................... ");
linhas.add("Inicia a compctação.......................... ");
//ConfiguracaoBackp cb =new ConfiguracaoBackp();
c.compactarParaZip(conf.getLocalGravar()+filepath+".zip", conf.getLocalGravar()+filepath);

log.info("Fim da Compactação ");
linhas.add("Fim da Compactação ");
log.info("Inicia envio de email.......................... ");
linhas.add("Inicia envio de email.......................... ");
Email email= new Email();
log.info("enviando email do arquivo..............: "+filepath+".zip");
email.enviarEmailComArquivo(conf.getLocalGravar()+filepath+".zip",conf);
log.info("Fim do envio de email ");
linhas.add("Fim do envio de email");
            
Toolkit.getDefaultToolkit().beep();
} else {
status = false;
log.info("Cópia de banco não foi criada " + conf.getNomeBanco()+ " in " + conf.getHost() + ":" + conf.getPorta());
linhas.add("Cópia de banco não foi criada " + conf.getNomeBanco()+ " in " + conf.getHost() + ":" + conf.getPorta());
}
 
} catch (IOException ioe) {
    linhas.add(ioe.toString()+ ioe.getCause().toString());
log.error(ioe, ioe.getCause());
    try {
        escrever(file);
    } catch (IOException ex) {
        Logger.getLogger(BackupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        escrever(file);
    } catch (IOException ex) {
        Logger.getLogger(BackupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (Exception e) {
log.error(e, e.getCause());
  linhas.add(e.toString()+ e.getCause().toString());
    try {
        escrever(file);
    } catch (IOException ex) {
        Logger.getLogger(BackupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   try {
        escrever(file);
//conf.getLocalMysql(), conf.getHost(), conf.getPorta() , conf.getUsuario(), conf.getSenha(), conf.getNomeBanco(), conf.getLocalGravar()
    } catch (IOException ex) {
        Logger.getLogger(BackupBean.class.getName()).log(Level.SEVERE, null, ex);
    }

return status;
}
 
   
   public boolean backupDataWithDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath) {
        System.out.println(dumpExePath+host+port+ user+  password+ database+ backupPath);
       boolean status = false;
        try {
            Process p = null;
 
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = "backup(with_DB)_" + database + "_" + host + "_(" + dateFormat.format(date) + ").sql";

            
            String batchCommand = "";
            if (password != "") {
                //Backup with database
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }
 
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();
 
            if (processComplete == 0) {
                status = true;
                log.info("Backup created successfully for with DB " + database + " in " + host + ":" + port);
            } else {
                status = false;
                log.info("Could not create the backup for with DB " + database + " in " + host + ":" + port);
            }
 
        } catch (IOException ioe) {
            log.error(ioe, ioe.getCause());
        } catch (Exception e) {
            log.error(e, e.getCause());
        }
        return status;
    }
   
   
  
           public void escrever (File path) throws IOException {
		
		long begin = System.currentTimeMillis();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                
                for (int i = 0; i < linhas.size(); i++) {
                   writer.write(linhas.get(i));
		writer.newLine();
               }
             
                
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
    
}


