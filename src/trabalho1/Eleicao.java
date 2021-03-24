package trabalho1;

import java.util.LinkedList;

public class Eleicao {
	public int processaNumVagas(LinkedList<Politico> politicos) {
		int cont = 0;
		
		for(Politico politico: politicos) {
			if(politico.getSituacao().equals("Eleito")) {
				cont++;
			}
		}
		
		return cont;
	}
	
	public void processaVotosTotaisPartidos(LinkedList<Partido> partidos) {
		for(Partido partido: partidos) {
			partido.setVotosTotais();
		}
	}
}
