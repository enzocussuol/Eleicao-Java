package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Leitura {
	public void lePoliticos(LinkedList<Politico> politicos){
		try {
			FileInputStream input = new FileInputStream("src/csv-exemplos/capitais/vitória-candidatos.csv");
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
				politico.setNome(linhaScanner.next());
				politico.setNomeUrna(linhaScanner.next());
				politico.setSexo(linhaScanner.next().charAt(0));
				
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate data = LocalDate.parse(linhaScanner.next(), formatador);
				
				politico.setDataNascimento(data);
				
				if(linhaScanner.next().equals("Válido")) {
					politico.setPartido(Integer.parseInt(linhaScanner.next()));
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
	
	public void lePartidos(LinkedList<Partido> partidos) {
		try {
			FileInputStream input = new FileInputStream("src/csv-exemplos/capitais/vitória-partidos.csv");
			Scanner inputScanner = new Scanner(input, "UTF-8");
			
			String linha;
			
			inputScanner.nextLine();
			while(inputScanner.hasNextLine()) {
				linha = inputScanner.nextLine();
				
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(",");
				
				Partido partido = new Partido();
				
				partido.setNumeroPartido(linhaScanner.nextInt());
				partido.setVotosLegenda(linhaScanner.nextInt());
				partido.setNomePartido(linhaScanner.next());
				partido.setSiglaPartido(linhaScanner.next());
				
				//Adicionar politicos na lista de politicos de cada partido
				
				partidos.add(partido);
				
				linhaScanner.close();
			}
			
			inputScanner.close();
		} catch(FileNotFoundException e){
			System.out.println("Nao foi possivel abrir o arquivo dos partidos");
			return;
		}
	}
}
