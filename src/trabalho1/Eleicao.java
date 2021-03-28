package trabalho1;

import java.util.LinkedHashMap;
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
	
	public void processaVotosTotaisPartidos(LinkedHashMap<Integer, Partido> partidos) {
		for(Integer chave: partidos.keySet()) {
			partidos.get(chave).setVotosTotais();
		}
	}
	
	public void processaNumEleitosPartidos(LinkedHashMap<Integer, Partido> partidos) {
		for(Integer chave: partidos.keySet()) {
			partidos.get(chave).setNumEleitos();
		}
	}
	
	public void processaPrimeiroColocadoPartidos(LinkedHashMap<Integer, Partido> partidos) {
		for(Integer chave: partidos.keySet()) {
			partidos.get(chave).setPrimeiroColocado();
		}
	}
	
	public void processaUltimoColocadoPartidos(LinkedHashMap<Integer, Partido> partidos) {
		for(Integer chave: partidos.keySet()) {
			partidos.get(chave).setUltimoColocado();
		}
	}
	
	public int processaTotalVotos(LinkedHashMap<Integer, Partido> partidos) {
		int totalVotos = 0;
		
		for(Integer chave: partidos.keySet()) {
			totalVotos += partidos.get(chave).getVotosTotais();
		}
		
		return totalVotos;
	}
	
	public int processaVotosLegenda(LinkedHashMap<Integer, Partido> partidos) {
		int votosLegenda = 0;
		
		for(Integer chave: partidos.keySet()) {
			votosLegenda += partidos.get(chave).getVotosLegenda();
		}
		
		return votosLegenda;
	}
	
	public int processaVotosNominais(int totalVotos, int votosLegenda) {
		return totalVotos - votosLegenda;
	}
}
