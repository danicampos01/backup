/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.contoller;

/**
 *
 * @author Daniel
 */
import backup.model.Backup;
import backup.model.ConfiguracaoBackp;
import backup.util.Compactador;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

    /**
     * Classe para manipular a execução de tarefas agendadas automaticamentes
     * @author Jean C Becker
     * @version 1.0
     */
public class GerarTarefasAgendadas {

    Timer timer;
    
    /**
     * Método para iniciar a execução das tarefas.
     */
     public static void main(String[] args) {
         GerarTarefasAgendadas g = new GerarTarefasAgendadas();
        
         
         
         
         g.iniciar();
     }
    public void iniciar() {
 ConfiguracaoBackp configuracaoBackup = new ConfiguracaoBackp();
        timer = new Timer();
        long interval = 0;
        
      
                if(configuracaoBackup.getIntervalo()==24){
            interval=1  * 1000 *60 * 60 * 24;
        }
        
                
                   if(configuracaoBackup.getIntervalo()==12){
            interval=1  * 1000 *60 * 60 * 12;
        }
                   
                      if(configuracaoBackup.getIntervalo()==1){
            interval=1  * 1000 *60 * 60;
        }
        
             
        //Executa tarefa a cada 24 horas a partir da primeira
        //       timer.schedule(new tarefasDiarias(),
        //        0,
        //        1 * 1000 * 60 * 60 * 24);

        
               //Executa tarefa a cada 24 horas a partir da primeira
               timer.schedule(new tarefasDiarias(),  0,  interval  );
        
        //Executa tarefa todo dia as 6 da manha
	//	Calendar calendar = Calendar.getInstance();
       // calendar.set(Calendar.HOUR_OF_DAY, 6);
      // // calendar.set(Calendar.MINUTE, 0);
     //   calendar.set(Calendar.SECOND, 0);
      //  Date time = calendar.getTime();
     //   timer.schedule(new tarefasDiarias(), time);
    }
    /**
     * Método para interromper a execução das tarefas.
     */
    public void parar() {
        timer.cancel();
    }
    /**
     * Método que contém as tarefas agendadas que serão executadas.
     */
    class tarefasDiarias extends TimerTask {

        public void run() {
            System.out.println("executou");
            ConfiguracaoBackp cb = new ConfiguracaoBackp();
            BackupBean bb = new BackupBean();
                              //C:\xampp\mysql\bin\mysqldump.exelocalhost3306rootmysqlC:/opt/
                              //C:\xampp\mysql\bin\mysqldump.exelocalhost3306rootmysqlC:/opt/
                              //C:\xampp\mysql\bin\mysqldump.exe localhost 3306 root  mysql C:/opt/
  ///b.backupDataWithDatabase("C:\\\\xampp\\\\mysql\\\\bin\\\\mysqldump.exe", "localhost", "3306", "root", "", "mysql", "C:/opt/");
                 
           // bb.backupDataWithOutDatabase(cb.getLocalMysql(), cb.getHost(), cb.getPorta() , cb.getUsuario(), cb.getSenha(), cb.getNomeBanco(), cb.getLocalGravar());
          
            //Compactador c = new Compactador();
//c.compactarParaZip(cb.getLocalGravar(), cb.getLocalGravar()+filepath);
        }
    }
}
