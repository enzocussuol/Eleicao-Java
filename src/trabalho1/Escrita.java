package trabalho1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Escrita {
	private void imprimePolitico(Politico politico, LinkedHashMap<Integer, Partido> partidos, int i) {
		System.out.print(i + " - ");
		System.out.print(politico.getNome() + " / ");
		System.out.print(politico.getNomeUrna() + " (");
		System.out.print(partidos.get(politico.getPartido()).getNome() + ", ");
		System.out.println(politico.getVotosNominais() + " votos)");
	}
	
	public void imprimeNumeroVagas(int numVagas) {
		System.out.print("Número de vagas: ");
		System.out.println(numVagas + "\n");
	}
	
	public void imprimeEleitos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Vereadores eleitos:");
		
		int i = 1;
		for(Politico politico: politicos) {
			if(politico.getSituacao().equals("Eleito")) {
				imprimePolitico(politico, partidos, i);
				i++;
			}
			if(i > numVagas) {
				break;
			}
		}
		
		System.out.println();
	}
	
	public void imprimeMaisVotados(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
		
		int i = 1;
		for(Politico politico: politicos) {
			if(i <= numVagas) {
				imprimePolitico(politico, partidos, i);
				i++;
			}else {
				break;
			}
		}
		
		System.out.println();
	}
	
	public void imprimeSeriamEleitos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos: (com sua posição no ranking de mais votados)");
		
		int i = 1;
		for(Politico politico: politicos) {
			if(i <= numVagas){
				if(politico.getSituacao().equals("Não eleito") || politico.getSituacao().equals("Suplente")) {
					imprimePolitico(politico, partidos, i);
				}
				i++;
			}else {
				break;
			}
		}
		
		System.out.println();
	}
	
	public void imprimeNaoSeriamEleitos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Eleitos, que se beneficiaram do sistema proporcional: (com sua posição no ranking de mais votados)");
		
		int i = 1;
		for(Politico politico: politicos) {
			if(i > numVagas) {
				if(politico.getSituacao().equals("Eleito")) {
					imprimePolitico(politico, partidos, i);
				}
			}
			i++;
		}
		
		System.out.println();
	}
	
	public void imprimeVotosPartidos(LinkedList<Map.Entry<Integer, Partido>> partidos) {
		System.out.println("Votação dos partidos e número de candidatos eleitos:");
		
		int i = 1;
		for(Map.Entry<Integer, Partido> entrada: partidos) {
			Partido partido = entrada.getValue();
			
			int votosTotais = partido.getVotosTotais();
			int votosLegenda = partido.getVotosLegenda();
			int votosNominais = votosTotais - votosLegenda;
			
			System.out.print(i + " - ");
			System.out.print(partido.getSigla() + " - ");
			System.out.print(partido.getNumero() + ", ");
			System.out.print(votosTotais + " votos (");
			System.out.print(votosNominais + " nominais e ");
			System.out.print(votosLegenda + " de legenda), ");
			
			int numEleitos = partido.getNumEleitos();
			if(numEleitos > 1) {
				System.out.println(numEleitos + " candidatos eleitos");
			}else {
				System.out.println(numEleitos + " candidatos eleito");
			}
			
			i++;
		}
		
		System.out.println();
	}
	
	public void imprimePrimeiroUltimoColocados(LinkedList<Map.Entry<Integer, Partido>> partidos) {
		System.out.println("Primeiro e último colocados de cada partido:");
		
		int i = 1;
		for(Map.Entry<Integer, Partido> entrada: partidos) {
			Partido partido = entrada.getValue();
			if(partido.getVotosTotais() != 0) {
				Politico primeiroColocado = partido.getPrimeiroColocado();
				Politico ultimoColocado = partido.getUltimoColocado();
				
				System.out.print(i + " - ");
				System.out.print(partido.getSigla() + " - ");
				System.out.print(partido.getNumero() + ", ");
				System.out.print(primeiroColocado.getNomeUrna() + " (");
				System.out.print(primeiroColocado.getNumero() + ", ");
				System.out.print(primeiroColocado.getVotosNominais() + " votos) / ");
				System.out.print(ultimoColocado.getNomeUrna() + " (");
				System.out.print(ultimoColocado.getNumero() + ", ");
				System.out.println(ultimoColocado.getVotosNominais() + " votos)");
				
				i++;
			}
		}
		
		System.out.println();
	}
	
	public void imprimeDistribuicaoFaixaEtaria(LinkedList<Politico> politicos, LocalDate dataEleicao, int numVagas) {
		System.out.println("Eleitos, por faixa etária (na data da eleição):");
		
		int ate30 = 0;
		int de30ate40 = 0;
		int de40ate50 = 0;
		int de50ate60 = 0;
		int maior60 = 0;
		
		int i = 1;
		for(Politico politico: politicos) {
			if(politico.getSituacao().equals("Eleito")){
				long idade = ChronoUnit.YEARS.between(politico.getDataNascimento(), dataEleicao);
				if(idade < 30) ate30++;
				else if(idade >= 30 && idade < 40) de30ate40++;
				else if(idade >= 40 && idade < 50) de40ate50++;
				else if(idade >= 50 && idade < 60) de50ate60++;
				else if(idade >= 60) maior60++;
				
				i++;
			}
			if(i > numVagas) {
				break;
			}
		}
		
		float p1 = (float)ate30/numVagas;
		p1 *= 100;
		float p2 = (float)de30ate40/numVagas;
		p2 *= 100;
		float p3 = (float)de40ate50/numVagas;
		p3 *= 100;
		float p4 = (float)de50ate60/numVagas;
		p4 *= 100;
		float p5 = (float)maior60/numVagas;
		p5 *= 100;
		
		System.out.print("      Idade < 30: ");
		System.out.print(ate30 + "(");
		System.out.printf("%.2f", p1);
		System.out.println("%)");
		
		System.out.print("30 <= " + "Idade < 40: ");
		System.out.print(de30ate40 + "(");
		System.out.printf("%.2f", p2);
		System.out.println("%)");
		
		System.out.print("40 <= " + "Idade < 50: ");
		System.out.print(de40ate50 + "(");
		System.out.printf("%.2f", p3);
		System.out.println("%)");
		
		System.out.print("50 <= " + "Idade < 60: ");
		System.out.print(de50ate60 + "(");
		System.out.printf("%.2f", p4);
		System.out.println("%)");
		
		System.out.print("60 <= " + "Idade" + "\t: ");
		System.out.print(maior60 + "(");
		System.out.printf("%.2f", p5);
		System.out.println("%)");
		
		System.out.println();
	}
	
	public void imprimeDistribuicaoSexo(LinkedList<Politico> politicos, int numVagas) {
		System.out.println("Eleitos, por sexo:");
		
		int feminino = 0;
		int masculino = 0;
		
		int i = 1;
		for(Politico politico: politicos) {
			if(politico.getSituacao().equals("Eleito")) {
				if(politico.getSexo() == 'F') {
					feminino++;
				}else {
					masculino++;
				}
				i++;
			}
			if(i > numVagas) {
				break;
			}
		}
		
		float p1 = (float)feminino/numVagas;
		p1 *= 100;
		float p2 = (float)masculino/numVagas;
		p2 *= 100;
		
		System.out.print("Feminino:  " + feminino + " (");
		System.out.printf("%.2f", p1);
		System.out.println("%)");
		
		System.out.print("Masculino: " + masculino + " (");
		System.out.printf("%.2f", p2);
		System.out.println("%)");
		
		System.out.println();
	}
	
	public void imprimeNumVotos(int totalVotos, int votosLegenda, int votosNominais) {
		System.out.println("Total de votos válidos:    " + totalVotos);
		
		float p1 = (float)votosNominais/totalVotos;
		p1 *= 100;
		float p2 = (float)votosLegenda/totalVotos;
		p2 *= 100;
		
		System.out.print("Total de votos nominais:   ");
		System.out.print(votosNominais);
		System.out.print(" (");
		System.out.printf("%.2f", p1);
		System.out.println("%)");
		
		System.out.print("Total de votos de Legenda: ");
		System.out.print(votosLegenda);
		System.out.print(" (");
		System.out.printf("%.2f", p2);
		System.out.println("%)");
	}
}
