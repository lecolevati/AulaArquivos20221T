package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController arqCont = new ArquivosController();
		
//		String caminho = "C:\\Users\\leco";
		String caminho = "E:\\TMP";
		String nome = "logo.png";
		int codigo = 2;
		try {
//			arqCont.leDir(caminho);
//			arqCont.escreveArquivo(caminho, nome);
//			arqCont.leArquivo(caminho, nome, codigo);
			arqCont.abreArquivo(caminho, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
