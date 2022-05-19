package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void leDir(String caminho) throws IOException {
		File diretorio = new File(caminho);
		if (diretorio.exists() && diretorio.isDirectory()) {
			File[] lista = diretorio.listFiles();
			for (File f : lista) {
				if (f.isDirectory()) {
					System.out.println("<DIR> "+f.getName());
				}
			}
			for (File f : lista) {
				if (f.isFile()) {
					System.out.println("      "+f.getName());
				}
			}
		} else {
			throw new IOException("Diretório Inválido");
		}
	}

	@Override
	public void leArquivo(String caminho, String nome, int codigo) throws IOException {
		File arquivo = new File(caminho, nome);
		if (arquivo.exists() && arquivo.isFile()) {
			FileInputStream leFluxo = new FileInputStream(arquivo);
			InputStreamReader converteFluxo = new InputStreamReader(leFluxo);
			BufferedReader buffer = new BufferedReader(converteFluxo);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				int cod = Integer.parseInt(vetLinha[0]);
				if (codigo == cod) {
					System.out.println(vetLinha[1] + "\t" + vetLinha[2]);
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			converteFluxo.close();
			leFluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}
	}

	@Override
	public void escreveArquivo(String caminho, String nome) throws IOException {
		String conteudo = geraConteudo();
		File arquivo = new File(caminho, nome);
		File diretorio = new File(caminho);
		if (diretorio.exists() && diretorio.isDirectory()) {
			boolean existe = false;
			if (arquivo.exists()) {
				existe = true;
			}
			
			//Sequência que cria e preenche um arquivo
			escrita(conteudo, arquivo, existe);
		} else {
			throw new IOException("Diretório Inválido");
		}
	}

	private void escrita(String conteudo, File arquivo, boolean existe) throws IOException {
		FileWriter abreArquivo = new FileWriter(arquivo, existe);
		PrintWriter escreveArquivo = new PrintWriter(abreArquivo);
		escreveArquivo.write(conteudo);
		escreveArquivo.flush();
		escreveArquivo.close();
		abreArquivo.close();
	}

	private String geraConteudo() {
		StringBuffer buffer = new StringBuffer();
		String frase = "";
		while (!frase.equals("fim")) {
			frase = JOptionPane.showInputDialog(null, "Digite uma frase");
			if (!frase.equals("fim")) {
				buffer.append(frase);
				buffer.append("\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void abreArquivo(String caminho, String nome) throws IOException {
		File arquivo = new File(caminho, nome);
		if (arquivo.exists() && arquivo.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(arquivo);			
		} else {
			throw new IOException("Arquivo inválido");
		}

	}

}
