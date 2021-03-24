package trabalho1;

import java.util.LinkedList;

public class Testador {
	public static void main(String[] args) {
		Leitura leitura = new Leitura();
		Escrita escrita = new Escrita();
		
		LinkedList<Politico> politicos = new LinkedList<Politico>();
		LinkedList<Partido> partidos = new LinkedList<Partido>();
	
		leitura.lePoliticos(politicos);
		leitura.lePartidos(partidos);
		
		}
}
