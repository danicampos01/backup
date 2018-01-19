/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiew;
import backup.model.ConfiguracaoBackp;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author Daniel
 */
public class Manipulador {
    public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:/var/dados.properties");
		props.load(file);
		return props;

	}

	public static void  main(String args[]) throws IOException {
		
            ConfiguracaoBackp conf = new ConfiguracaoBackp();
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
		
	
	}

}
