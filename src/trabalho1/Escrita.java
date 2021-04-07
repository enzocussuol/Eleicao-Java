package trabalho1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * A classe escrita tem como proposta gerar nos arquivos de saída os resultados esperados,
 * entre eles, alguns dados estatisticos da eleição, além de listas de candidatos conforme diferentes parametros
 *
 */
public class Escrita {
	/**
	 * imprime os dados de um politico
	 * @param politico politico que sera impresso
	 * @param partidos Hash com todos os partidos
	 * @param i indica a posição do partido desejado na Hash
	 */
	private void imprimePolitico(Politico politico, LinkedHashMap<Integer, Partido> partidos, int i) {
		System.out.print(i + " - ");
		System.out.print(politico.getNome() + " / ");
		System.out.print(politico.getNomeUrna() + " (");
		System.out.print(partidos.get(politico.getPartido()).getSigla() + ", ");

		int votosNominais = politico.getVotosNominais();
		if(votosNominais > 1) {
			System.out.println(votosNominais + " votos)");
		}else {
			System.out.println(votosNominais + " voto)");
		}
	}
	
	/**
	 * imprime o numero de vagas da eleicao (numero de politicos eleitos)
	 * @param numVagas, numero de vagas
	 */
	public void imprimeNumeroVagas(int numVagas) {
		System.out.print("Número de vagas: ");
		System.out.println(numVagas + "\n");
	}
	
	/**
	 * imprime uma lista com todos politicos eleitos
	 * @param politicos lista com todos politicos
	 * @param partidos hashmap com os partidos
	 * @param numVagas numero de vagas que serve como critério de parada
	 */
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
	
	/**
	 * imprime os candidatos mais votados da eleição
	 * @param politicos lista com todos politicos
	 * @param partidos hashmap com os partidos
	 * @param numVagas numero de vagas que serve como critério de parada
	 */
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
	
	/**
	 * imprime os politicos que não foram eleitos, mas seriam em uma votacao considerando apenas numero de votos
	 * @param politicos lista com todos politicos
	 * @param partidos hashmap com os partidos
	 * @param numVagas numero de vagas que serve como critério de parada
	 */
	public void imprimeSeriamEleitos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
		
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
	
	/**
	 * imprime os politicos que foram eleitos, mas nao seriam em uma votacao considerando apenas numero de votos
	 * @param politicos lista com todos politicos
	 * @param partidos hashmap com os partidos
	 * @param numVagas numero de vagas que serve como critério de parada
	 */
	public void imprimeNaoSeriamEleitos(LinkedList<Politico> politicos, LinkedHashMap<Integer, Partido> partidos, int numVagas) {
		System.out.println("Eleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
		
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
	
	/**
	 * imprime a quantidade de votos (nominais e de legenda) de cada partido e o numero de candidatos eleitos
	 * @param partidos hashmap com todos partidos
	 */
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

			if(votosTotais > 1) {
				System.out.print(votosTotais + " votos (");				
			}else {
				System.out.print(votosTotais + " voto (");	
			}

			if(votosNominais > 1) {
				System.out.print(votosNominais + " nominais e ");
			}else {
				System.out.print(votosNominais + " nominal e ");
			}

			System.out.print(votosLegenda + " de legenda), ");
			
			int numEleitos = partido.getNumEleitos();
			if(numEleitos > 1) {
				System.out.println(numEleitos + " candidatos eleitos");
			}else {
				System.out.println(numEleitos + " candidato eleito");
			}
			
			i++;
		}
		
		System.out.println();
	}
	
	/**
	 * imprime os melhores e piores colocados de cada um dos partidos
	 * @param partidos hashmap com todos partidos
	 */
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

				int votosNominais = primeiroColocado.getVotosNominais();
				if(votosNominais > 1) {
					System.out.print(votosNominais + " votos) / ");
				}else {
					System.out.print(votosNominais + " voto) / ");
				}

				System.out.print(ultimoColocado.getNomeUrna() + " (");
				System.out.print(ultimoColocado.getNumero() + ", ");

				votosNominais = ultimoColocado.getVotosNominais();
				if(votosNominais > 1) {
					System.out.println(votosNominais + " votos)");
				}else {
					System.out.println(votosNominais + " voto)");
				}
				
				i++;
			}
		}
		
		System.out.println();
	}
	
	/**
	 * imprime uma tabela com dados sobre a faixa etaria de todos politicos
	 * @param politicos lista com todos politicos
	 * @param partidos hashmap com os partidos
	 * @param numVagas numero de vagas que serve como critério de parada
	 */
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
	
	/**
	 * imprime uma tabela com dados sobre o sexo dos politicos eleitos
	 * @param politicos lista com todos politicos
	 * @param numVagas numero de vagas
	 */
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
	
	/**
	 * imprime o numero de votos da eleicao
	 * @param totalVotos quantidade total de votos 
	 * @param votosLegenda quantidade de votos nos partidos
	 * @param votosNominais quantidade de votos diretamente nos politicos
	 */
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
