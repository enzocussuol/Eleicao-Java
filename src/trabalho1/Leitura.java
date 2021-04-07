package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 
 * A classe Leitura tem como proposta coletar os dados dos arquivos de entrada e os armazenar corretamente.
 *
 */
public class Leitura {
	/**
	 * Realiza a leitura do arquivo de partidos e preenche o hashmap com todos os partidos
	 * @param partidos HashMap que armazena todos os partidos
	 * @param arquivo String que contém o nome do arquivo onde encontra-se as informações sobre os partidos
	 */
	public void lePartidos(LinkedHashMap<Integer, Partido> partidos, String arquivo) {
		try {
			FileInputStream input = new FileInputStream(arquivo);
			Scanner inputScanner = new Scanner(input, "UTF-8");
			
			String linha;
			
			inputScanner.nextLine();
			while(inputScanner.hasNextLine()) {
				linha = inputScanner.nextLine();
				
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(",");
				
				Partido partido = new Partido();
				
				partido.setNumero(linhaScanner.nextInt());
				partido.setVotosLegenda(linhaScanner.nextInt());
				partido.setNome(linhaScanner.next().toUpperCase());
				partido.setSigla(linhaScanner.next());
				
				partidos.put(partido.getNumero(), partido);
				
				linhaScanner.close();
			}
			
			inputScanner.close();
		} catch(FileNotFoundException e){
			System.out.println("Nao foi possivel abrir o arquivo dos partidos");
			return;
		}
	}
	
	/**
	 * Realiza a leitura do arquivo de politicos e preenche a lista com todos os politicos
	 * @param politicos LinkedList que armazena todos os politicos
	 * @param partidos HashMap que armazena todos os partidos
	 * @param arquivo String que contém o nome do arquivo onde encontra-se as informações sobre os politicos
	 */
	public void lePoliticos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, String arquivo){
		try {
			FileInputStream input = new FileInputStream(arquivo);
			Scanner inputScanner = new Scanner(input, "UTF-8");
			
			String linha;
			
			inputScanner.nextLine();
			while(inputScanner.hasNextLine()) {
				linha = inputScanner.nextLine();
				
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(",");
				
				Politico politico = new Politico();
				
				politico.setNumero(Integer.parseInt(linhaScanner.next()));
				politico.setVotosNominais(Integer.parseInt(linhaScanner.next()));
				politico.setSituacao(linhaScanner.next());
				politico.setNome(linhaScanner.next().toUpperCase());
				politico.setNomeUrna(linhaScanner.next().toUpperCase());
				politico.setSexo(linhaScanner.next().charAt(0));
				
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate data = LocalDate.parse(linhaScanner.next(), formatador);
				
				politico.setDataNascimento(data);
				
				if(linhaScanner.next().equals("Válido")) {
					politico.setPartido(Integer.parseInt(linhaScanner.next()));
					partidos.get(politico.getPartido()).getPoliticos().add(politico);
					
					politicos.add(politico);
				}else {
					linhaScanner.next();
				}
				
				linhaScanner.close();
			}
			
			inputScanner.close();
		} catch(FileNotFoundException e){
			System.out.println("Nao foi possivel abrir o arquivo de leitura dos politicos");
			return;
		}
	}
}
