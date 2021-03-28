package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Leitura {
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
				partido.setSigla(linhaScanner.next().toUpperCase());
				
				partidos.put(partido.getNumero(), partido);
				
				linhaScanner.close();
			}
			
			inputScanner.close();
		} catch(FileNotFoundException e){
			System.out.println("Nao foi possivel abrir o arquivo dos partidos");
			return;
		}
	}
	
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
