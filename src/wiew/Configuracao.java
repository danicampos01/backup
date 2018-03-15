/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiew;

import backup.contoller.BackupBean;
import backup.model.ConfiguracaoBackp;
import com.mysql.fabric.xmlrpc.base.Value;
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static wiew.Manipulador.getProp;

/**
 *
 * @author Daniel
 */
public final class Configuracao extends javax.swing.JFrame {

       ConfiguracaoBackp conf = new ConfiguracaoBackp();
      Main main = new Main();
      
      
    /**
     * Creates new form EmailBackup
     */
    public Configuracao() throws IOException {
        initComponents();
        carregaDados();
        carregaDadosTela();
main.setVisible(false);
       /// main.setVisible(true);
final TrayIcon trayIcon; // declarando uma constante do tipo TrayIcon

 

    // Aqui vamos testar se o recurso é suportado

    if (SystemTray.isSupported()) {

   

    //declarando uma variavel  do tipo SystemTray

    SystemTray tray = SystemTray.getSystemTray();

   

    //declarando uma variavel  do tipo Image que contera a imagem tray.gif

    Image image = Toolkit.getDefaultToolkit().getImage("C:/ERPSaude/backup/ico.gif");

   

   

     //Criamos um listener para escutar os eventos de mouse

      

    MouseListener mouseListener = new MouseListener() {

               

        public void mouseClicked(MouseEvent e) {

            

        }

 

        public void mouseEntered(MouseEvent e) {

           

        }

 

        public void mouseExited(MouseEvent e) {

           

        }

 

        public void mousePressed(MouseEvent e) {           

           

        //Toda vez que for clicado imprime uma mensagem na tela

            System.out.println("Tray Icon - O Mouse foi pressionado!");                       

        }

 

        public void mouseReleased(MouseEvent e) {

           

        }

    };

 

    // Criamos um ActionListener para a ação de encerramento do programa.

    ActionListener exitListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            //Imprime uma mensagem de despedida na tela

            System.out.println("Saindo...");

            JOptionPane.showMessageDialog(null,"Saindo...");

            System.exit(0);

        }

    };

      ActionListener mostrastatusglistener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            main.setVisible(true);
          //  JOptionPane.showMessageDialog(null,"Java 6 é uma revolução.");

        }

    };

    // Criamos um ActionListener para a exibir uma mensagem na tela ao clicarmos

    //em um item do menu.

    ActionListener mostramsglistener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            setVisible(true);
          //  JOptionPane.showMessageDialog(null,"Java 6 é uma revolução.");

        }

    };

   ActionListener mostraexecutabackuplistener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            new Thread(t1).start();

        }

    };

    //Criando um objeto PopupMenu

    PopupMenu popup = new PopupMenu("Menu de Opções");

    //criando itens do menu

    MenuItem mostramsg = new MenuItem("Configuração");
    
    MenuItem mostrastatus = new MenuItem("Status Backup");
    
    MenuItem mostraexecutabackup = new MenuItem("Executar Backup");

    MenuItem defaultItem = new MenuItem("Sair");
    

    //na linha a seguir associamos os objetos aos eventos
    
    mostrastatus.addActionListener(mostrastatusglistener);
    

    mostramsg.addActionListener(mostramsglistener);
    
    mostraexecutabackup.addActionListener(mostraexecutabackuplistener);

    defaultItem.addActionListener(exitListener);

    //Adicionando itens ao PopupMenu

    popup.add(mostramsg);
    popup.add(mostraexecutabackup);
    popup.add(mostrastatus);

    //adiconando um separador

    popup.addSeparator();

    //Criando objetos do tipo Checkbox

    CheckboxMenuItem cheque1 = new CheckboxMenuItem("Ativar Plugins");

    popup.add(cheque1);

    CheckboxMenuItem cheque2 = new CheckboxMenuItem("Iniciar Minimizado");

    popup.add(cheque2);

    popup.addSeparator();

    //Criando um submenu

    PopupMenu popup2 = new PopupMenu("SubMenu de Opções");

    MenuItem mostramsg2 = new MenuItem("Item1");

    MenuItem mostramsg3 = new MenuItem("Item2");

    MenuItem mostramsg4 = new MenuItem("Item3");

    popup2.add(mostramsg2);

    popup2.add(mostramsg3);

    popup2.add(mostramsg4);

    popup.add(popup2);

    popup.addSeparator();

    popup.add(defaultItem);
    
        trayIcon = new TrayIcon(image, "Monitoramento ERPSaude", popup);
        
         long interval = 0;
        
      
                if(conf.getIntervalo()==24){
            interval=1  * 1000 *60 * 60 * 24; //24 horas
        }
        
                
                   if(conf.getIntervalo()==12){
            interval=1  * 1000 *60 * 60 * 12; //12 horas
        }
                   
                      if(conf.getIntervalo()==6){
            interval=1  * 1000 *60 * 60 * 6; //6 horas hora
        }
                      
                          if(conf.getIntervalo()==1){
            interval=1  * 1000 *60 * 60; //uma hora
        }
                          
                          if(conf.getIntervalo()==10){
            interval=1  * 1000 *60; //um minuto
        }
                          
                           if(conf.getIntervalo()==30){
            interval= 1 * 1000 * 30; //meio minuto
        }
 //long TEMPO = (1000 * 10);  
 
long TEMPO = interval;
   System.out.println("inicio");
		Timer timer = null;
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
                                            
                                            trayIcon.displayMessage("Action Event",

                "Backup iniciado",

                TrayIcon.MessageType.INFO);
                                           
                                            if("1".equals(conf.getStatusBackup())){
                                                
                                                 main.dispose();
                                            main.read(new File(conf.getArquivoLog()));
                                              main.setVisible(true);
                                            }
                                            
                                           
                                          
                                                BackupBean bb = new BackupBean();
                                                bb.backupDataWithOutDatabase(conf);
                                                 
                                                 if("1".equals(conf.getStatusBackup())){
                                                
                                                 main.dispose();
                                            main.read(new File(conf.getArquivoLog()));
                                              main.setVisible(true);
                                            }
                                                  
                                            
						System.out.println("Teste agendador");
						//chamar metodo
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}
 
    //criando um objeto do tipo TrayIcon

 

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            trayIcon.displayMessage("Action Event",

                "Um Evento foi disparado",

                TrayIcon.MessageType.INFO);

        }

    };

    

    //Na linha a seguir a imagem a ser utilizada como icone sera redimensionada

    trayIcon.setImageAutoSize(true);

    //Seguida adicionamos os actions listeners

    trayIcon.addActionListener(actionListener);

    trayIcon.addMouseListener(mouseListener);

   

    //Tratamento de erros

    try {

        tray.add(trayIcon);

    } catch (AWTException e) {

        System.err.println("Erro, TrayIcon não sera adicionado.");

    }

 

} else {

 

        //Caso o item  System Tray não for suportado

        JOptionPane.showMessageDialog(null,"recurso ainda não esta disponível pra o seu sistema");

}
    }
    
        public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:/ERPSaude/backup/dados.properties");
		props.load(file);
		return props;

	}
    public void carregaDadosTela(){
        
      tf_emal.setText(conf.getEmailDestinatario());
      tf_em_porta.setText(conf.getEmailPorta());
      tf_email_senha.setText(conf.getEmailSenha());
      tf_emal_mesagem.setText(conf.getMensagem());
      tf_emal_remetente.setText(conf.getEmailRemetente());
      tf_emal_titulo.setText(conf.getTitulo());
      tf_host.setText(conf.getEmailHost());
      tf_smtp.setText(conf.getSmtp());
           
      
      tf_bitesso.setText(conf.getBitesSo());
      tf_grava_arquivo_compctado.setText(conf.getLocalGravar());
      tf_host_ssgdb.setText(conf.getHost());
      tf_local_grava_copia.setText(conf.getLocalGravar());
      tf_local_mysql.setText(conf.getLocalMysql());
      tf_nome_do_banco.setText(conf.getNomeBanco());
      tf_portasgdb.setText(conf.getPorta());
      tf_usuario.setText(conf.getUsuario());
      tf_so.setText(conf.getSo());
      tf_senhaSGDB.setText(conf.getSenha());
      tf_intervalo.setText(String.valueOf(conf.getIntervalo()));
      
 
        
        
    }    
        
        
   public void carregaDados() throws IOException{
       
             
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
                
                
                conf.setBitesSo((prop.getProperty("prop.server.biteso")));
                conf.setEmailDestinatario((prop.getProperty("prop.server.emaildestinatario")));
                conf.setGuardarArquivo((prop.getProperty("prop.server.guardararquivo")));
                conf.setHost((prop.getProperty("prop.server.host")));
                conf.setLocalGravar((prop.getProperty("prop.server.localgravar")));
                conf.setLocalMysql((prop.getProperty("prop.server.localmysql")));
                conf.setNomeBanco((prop.getProperty("prop.server.nomebanco")));
                conf.setPorta((prop.getProperty("prop.server.porta")));
                conf.setSenha((prop.getProperty("prop.server.senha")));
                conf.setSo((prop.getProperty("prop.server.so")));
                conf.setUsuario((prop.getProperty("prop.server.usuario")));
                conf.setArquivoLog((prop.getProperty("prop.server.arquivolog")));
               
                
                conf.setEmailPorta((prop.getProperty("prop.server.emailporta")));
                conf.setEmailRemetente((prop.getProperty("prop.server.emailremetente")));
                conf.setEmailHost((prop.getProperty("prop.server.hostemail")));
                conf.setEmailSenha(prop.getProperty("prop.server.emailsenha"));
                conf.setSmtp(prop.getProperty("prop.server.smtp"));
                conf.setMensagem(prop.getProperty("prop.server.mensagem"));
                conf.setTitulo(prop.getProperty("prop.server.titulo"));
                conf.setIntervalo(Long.valueOf(prop.getProperty("prop.server.intervalo")));
                conf.setStatusBackup(prop.getProperty("prop.server.statusbackup"));
    

   }
   
   private  Runnable t1 = new Runnable() {
        public void run() {
            try{
                main.dispose();
                main.read(new File(conf.getArquivoLog()));
                main.setVisible(true);
                BackupBean bb = new BackupBean();
                bb.backupDataWithOutDatabase(conf);
                main.dispose();
                main.read(new File(conf.getArquivoLog()));
                main.setVisible(true);
                 Thread.currentThread().interrupt();
            } catch (Exception e){}
 
        }
    };
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_emal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_emal_titulo = new javax.swing.JTextField();
        tf_em_porta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_smtp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_host = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_emal_remetente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_emal_mesagem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_email_senha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf_local_mysql = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_local_grava_copia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_so = new javax.swing.JTextField();
        tf_nome_do_banco = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_bitesso = new javax.swing.JTextField();
        tf_grava_arquivo_compctado = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        tf_usuario = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_host_ssgdb = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tf_portasgdb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tf_senhaSGDB = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_intervalo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Email destino");

        tf_emal.setText("jTextField1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tf_emal, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_emal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Senha");

        jLabel3.setText("Email Remetente");

        tf_emal_titulo.setText("jTextField1");

        tf_em_porta.setText("jTextField1");

        jLabel4.setText("Porta");

        tf_smtp.setText("jTextField1");

        jLabel5.setText("SMTP");

        tf_host.setText("jTextField1");

        jLabel6.setText("Host");

        tf_emal_remetente.setText("jTextField1");

        jLabel7.setText("Email Título");

        tf_emal_mesagem.setText("jTextField1");

        jLabel8.setText("Email Mensagem");

        tf_email_senha.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tf_emal_titulo)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(tf_emal_mesagem, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(tf_emal_remetente, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(tf_host)
                    .addComponent(tf_smtp)
                    .addComponent(tf_em_porta)
                    .addComponent(tf_email_senha))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_emal_remetente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_email_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_em_porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_smtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_emal_mesagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_emal_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Email", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("SO");

        jLabel10.setText("Bites SO");

        tf_local_mysql.setText("jTextField1");

        jLabel11.setText("Local Mysql");

        tf_local_grava_copia.setText("jTextField1");

        jLabel12.setText("Local de gravação Backup");

        tf_so.setText("jTextField1");

        tf_nome_do_banco.setText("jTextField1");

        jLabel13.setText("Nome do Banco");

        tf_bitesso.setText("jTextField1");

        tf_grava_arquivo_compctado.setText("jTextField1");

        jLabel15.setText("Local de gravação arquivo compactado");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(tf_grava_arquivo_compctado, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(tf_bitesso)
                    .addComponent(tf_nome_do_banco)
                    .addComponent(tf_local_grava_copia)
                    .addComponent(tf_local_mysql)
                    .addComponent(tf_so))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_bitesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_so, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_local_mysql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_local_grava_copia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nome_do_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_grava_arquivo_compctado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tf_usuario.setText("jTextField1");

        jLabel17.setText("Usuario SGDB");

        tf_host_ssgdb.setText("jTextField1");
        tf_host_ssgdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_host_ssgdbActionPerformed(evt);
            }
        });

        jLabel16.setText("Host SGDB");

        tf_portasgdb.setText("jTextField1");

        jLabel14.setText("Porta SGDB");

        jLabel18.setText("Senha SGDB");

        tf_senhaSGDB.setText("jTextField1");

        jLabel19.setText("Intervalo de backup");

        tf_intervalo.setText("jTextField1");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(tf_portasgdb, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(tf_usuario)
                    .addComponent(tf_host_ssgdb)
                    .addComponent(jLabel18)
                    .addComponent(tf_senhaSGDB)
                    .addComponent(jLabel19)
                    .addComponent(tf_intervalo))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_portasgdb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_host_ssgdb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_senhaSGDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_intervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuração", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        setBounds(0, 0, 714, 572);
    }// </editor-fold>//GEN-END:initComponents

    private void tf_host_ssgdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_host_ssgdbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_host_ssgdbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configuracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Configuracao();
                } catch (IOException ex) {
                    Logger.getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tf_bitesso;
    private javax.swing.JTextField tf_em_porta;
    private javax.swing.JPasswordField tf_email_senha;
    private javax.swing.JTextField tf_emal;
    private javax.swing.JTextField tf_emal_mesagem;
    private javax.swing.JTextField tf_emal_remetente;
    private javax.swing.JTextField tf_emal_titulo;
    private javax.swing.JTextField tf_grava_arquivo_compctado;
    private javax.swing.JTextField tf_host;
    private javax.swing.JTextField tf_host_ssgdb;
    private javax.swing.JTextField tf_intervalo;
    private javax.swing.JTextField tf_local_grava_copia;
    private javax.swing.JTextField tf_local_mysql;
    private javax.swing.JTextField tf_nome_do_banco;
    private javax.swing.JTextField tf_portasgdb;
    private javax.swing.JTextField tf_senhaSGDB;
    private javax.swing.JTextField tf_smtp;
    private javax.swing.JTextField tf_so;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
}
