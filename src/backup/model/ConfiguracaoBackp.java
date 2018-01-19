/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.model;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class ConfiguracaoBackp {
  
    private String bitesSo;
    private String so;
    private String localMysql;
    private String localGravar;
    private String nomeBanco;
    private long intervalo;
    private String guardarArquivo;
    private String porta;
    private String usuario;
    private String senha;
    private String host;
    
   private String emailDestinatario;
   private String emailRemetente;
   private String smtp;
   private String emailPorta;
   private String emailHost;
   private String mensagem;
   private String titulo;
   private String emailSenha;
   private String arquivoLog;
   private String statusBackup;


    
    
    public ConfiguracaoBackp(){
        
     // bitesSo=64;
     // so= "Windows8.1";
     // localMysql="C:\\xampp\\mysql\\bin\\mysqldump.exe";
     // localGravar="C:/opt/";
    //  nomeBanco="ifpa";
     // intervalo=1;
     // guardarArquivo="C:/opt/"; //compactado
     // porta="3306";
      //usuario="ifpa";
     // senha="ifpa";
     // host="localhost";
      
      
        
    }
    /**
     * @return the bitesSo
     */
    public String getBitesSo() {
        return bitesSo;
    }

    /**
     * @param bitesSo the bitesSo to set
     */
    public void setBitesSo(String bitesSo) {
        this.bitesSo = bitesSo;
    }

    /**
     * @return the so
     */
    public String getSo() {
        return so;
    }

    /**
     * @param so the so to set
     */
    public void setSo(String so) {
        this.so = so;
    }

    /**
     * @return the localMysql
     */
    public String getLocalMysql() {
        return localMysql;
    }

    /**
     * @param localMysql the localMysql to set
     */
    public void setLocalMysql(String localMysql) {
        this.localMysql = localMysql;
    }

    /**
     * @return the localGravar
     */
    public String getLocalGravar() {
        return localGravar;
    }

    /**
     * @param localGravar the localGravar to set
     */
    public void setLocalGravar(String localGravar) {
        this.localGravar = localGravar;
    }

    /**
     * @return the nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * @param nomeBanco the nomeBanco to set
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    /**
     * @return the intervalo
     */
    public Long getIntervalo() {
        return intervalo;
    }

    /**
     * @param intervalo the intervalo to set
     */
    public void setIntervalo(Long intervalo) {
        this.intervalo = intervalo;
    }

    /**
     * @return the guardarArquivo
     */
    public String getGuardarArquivo() {
        return guardarArquivo;
    }

    /**
     * @param guardarArquivo the guardarArquivo to set
     */
    public void setGuardarArquivo(String guardarArquivo) {
        this.guardarArquivo = guardarArquivo;
    }

    /**
     * @return the porta
     */
    public String getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(String porta) {
        this.porta = porta;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the emailDestinatario
     */
    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    /**
     * @param emailDestinatario the emailDestinatario to set
     */
    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    /**
     * @return the emailRemetente
     */
    public String getEmailRemetente() {
        return emailRemetente;
    }

    /**
     * @param emailRemetente the emailRemetente to set
     */
    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    /**
     * @return the smtp
     */
    public String getSmtp() {
        return smtp;
    }

    /**
     * @param smtp the smtp to set
     */
    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    /**
     * @return the emailPorta
     */
    public String getEmailPorta() {
        return emailPorta;
    }

    /**
     * @param emailPorta the emailPorta to set
     */
    public void setEmailPorta(String emailPorta) {
        this.emailPorta = emailPorta;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the emailHost
     */
    public String getEmailHost() {
        return emailHost;
    }

    /**
     * @param emailHost the emailHost to set
     */
    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    /**
     * @return the emailSenha
     */
    public String getEmailSenha() {
        return emailSenha;
    }

    /**
     * @param emailSenha the emailSenha to set
     */
    public void setEmailSenha(String emailSenha) {
        this.emailSenha = emailSenha;
    }

    /**
     * @return the arquivoLog
     */
    public String getArquivoLog() {
        return arquivoLog;
    }

    /**
     * @param arquivoLog the arquivoLog to set
     */
    public void setArquivoLog(String arquivoLog) {
        this.arquivoLog = arquivoLog;
    }

    /**
     * @return the statusBackup
     */
    public String getStatusBackup() {
        return statusBackup;
    }

    /**
     * @param statusBackup the statusBackup to set
     */
    public void setStatusBackup(String statusBackup) {
        this.statusBackup = statusBackup;
    }

    
}
