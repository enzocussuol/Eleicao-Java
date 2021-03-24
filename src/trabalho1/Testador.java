package trabalho1;

import java.util.Collections;
import java.util.LinkedList;

public class Testador {
	public static void main(String[] args) {
		Leitura leitura = new Leitura();
		Escrita escrita = new Escrita();
		
		LinkedList<Politico> politicos = new LinkedList<Politico>();
		LinkedList<Partido> partidos = new LinkedList<Partido>();
		
		Eleicao eleicao = new Eleicao();
	
		leitura.lePoliticos(politicos);
		leitura.lePartidos(partidos);
		
		//eleicao.processaVotosTotaisPartidos(partidos);
		
		Collections.sort(politicos);
		
		for(Politico politico: politicos) {
			System.out.println(politico.toString());
		}
		
//		Collections.sort(partidos, new VotosTotaisComparator());
//		
//		for(Partido partido: partidos) {
//			System.out.println(partido.toString());
//		}
	}
}
