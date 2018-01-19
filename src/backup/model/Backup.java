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
public class Backup {

  private Date dataExecutado;
  private Double tamanho;
  private String nome;
  

  public Backup(){
      
      dataExecutado=new Date();
      
  }
    /**
     * @return the dataExecutado
     */
    public Date getDataExecutado() {
        return dataExecutado;
    }

    /**
     * @param dataExecutado the dataExecutado to set
     */
    public void setDataExecutado(Date dataExecutado) {
        this.dataExecutado = dataExecutado;
    }

    /**
     * @return the tamanho
     */
    public Double getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
