/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.contoller;

import static backup.contoller.BackupBean.log;
import backup.model.ConfiguracaoBackp;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email{
static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Email.class.getName());
    public void enviarEmailComArquivo(String file, ConfiguracaoBackp conf){
 
 

  String to = conf.getEmailDestinatario().trim();
  String from = conf.getEmailRemetente().trim();
  String host = conf.getEmailHost().trim();
  String filename = file;
  String msgText1 = conf.getMensagem();
  String subject = conf.getTitulo();
  log.info("TO: "+conf.getEmailRemetente().trim());
  log.info("FROM: "+conf.getEmailDestinatario().trim());
  log.info("HOST: "+conf.getEmailHost().trim());
  log.info("FILE: "+file);
  // cria algumas propriedades e obtem uma sessao padrao
  Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.socketFactory.port", conf.getEmailPorta().trim());
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", conf.getEmailPorta().trim());
            props.put("mail.smtp.starttls.enable", "true");  
            
            //# obs a porta pode ser a 587 ou a 465 do gmail
    //props.put("mail.smtp.auth", "true");
    
   // props.put("mail.debug", "true");
   // props.put("mail.smtp.debug", "true");
   // props.put("mail.mime.charset", "ISO-8859-1");
    //    props.put ("mail.smtp.starttls.enable", "true");
     //   props.put ("mail.smtp.socketFactory.fallback", "false");
     //   props.put ("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            
     /*Servidor	smtpHostMail	smtpPortMail	smtpStarttls
Gmail	smtp.gmail.com	587	true
Bol	smtps.bol.com.br	587	true
Ibest	smtp.ibest.com.br	587	true
IG	smtp.ig.com.br	587	true
Hotmail	smtp.live.com	25	true
     */
            
    log.info("PORTA: "+conf.getEmailPorta());
         Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication(from, conf.getEmailSenha().trim());
                             }
                        });

  try 
  {
      // cria a mensagem
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(subject);

      // cria a primeira parte da mensagem
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1);

      // cria a segunda parte da mensage
      MimeBodyPart mbp2 = new MimeBodyPart();

            // anexa o arquivo na mensagem
         FileDataSource fds = new FileDataSource(filename);
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(fds.getName());

      // cria a Multipart
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);

      // adiciona a Multipart na mensagem
      msg.setContent(mp);

      // configura a data: cabecalho
      msg.setSentDate(new Date());
      
      // envia a mensagem
      Transport.send(msg);
      log.info("email enviado");
  } 
  catch (MessagingException mex) 
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
  }
    }
    
    
     public void enviarEmvailSimples() {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("seuemail@gmail.com", "suasenha123");
                             }
                        });
            /** Ativa Debug para sessão */
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("seuemail@gmail.com")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("seuamigo@gmail.com, seucolega@hotmail.com, seuparente@yahoo.com.br");  
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Enviando email com JavaMail");//Assunto
                  message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  System.out.println("Feito!!!");
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}