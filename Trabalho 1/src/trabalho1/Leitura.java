package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Leitura {
	public void lePoliticos(LinkedList<Politico> politicos){
		try {
			FileInputStream input = new FileInputStream("src/csv-exemplos/capitais/vitória-candidatos.csv");
			Scanner scanner = new Scanner(input, "UTF-8");
			
			System.out.println(scanner.nextLine());
			
			scanner.close();
		} catch(FileNotFoundException e){
			System.out.println("Nao foi possivel ler o arquivo");
			return;
		}
	}
	
	public void lePartidos(LinkedList<Partido> partidos) {
		
	}
}
