package trabalho1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Testador {
	public static void main(String[] args) {
		Leitura leitura = new Leitura();
		Escrita escrita = new Escrita();
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataEleicao = LocalDate.parse(args[2], formatador);
		
		LinkedHashMap<Integer, Partido> mapaPartidos = new LinkedHashMap<Integer, Partido>();
		LinkedList<Politico> listaPoliticos = new LinkedList<Politico>();
		
		Eleicao eleicao = new Eleicao();
	
		leitura.lePartidos(mapaPartidos, args[1]);
		leitura.lePoliticos(listaPoliticos, mapaPartidos, args[0]);
		
		int numVagas = eleicao.processaNumVagas(listaPoliticos);
		
		eleicao.processaVotosTotaisPartidos(mapaPartidos);
		eleicao.processaNumEleitosPartidos(mapaPartidos);
		eleicao.processaPrimeiroColocadoPartidos(mapaPartidos);
		eleicao.processaUltimoColocadoPartidos(mapaPartidos);
		
		int totalVotos = eleicao.processaTotalVotos(mapaPartidos);
		int votosLegenda = eleicao.processaVotosLegenda(mapaPartidos);
		int votosNominais = eleicao.processaVotosNominais(totalVotos, votosLegenda);
		
		Collections.sort(listaPoliticos);
		
		escrita.imprimeNumeroVagas(numVagas);
		escrita.imprimeEleitos(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeMaisVotados(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeSeriamEleitos(listaPoliticos, mapaPartidos, numVagas);
		escrita.imprimeNaoSeriamEleitos(listaPoliticos, mapaPartidos, numVagas);
		
		LinkedList<Map.Entry<Integer, Partido>> listaPartidos = new LinkedList<Map.Entry<Integer, Partido>>(mapaPartidos.entrySet());
		
		Collections.sort(listaPartidos, new VotosTotaisComparator());
		escrita.imprimeVotosPartidos(listaPartidos);
		
		Collections.sort(listaPartidos, new VotosPrimeiroColocadoComparator());
		escrita.imprimePrimeiroUltimoColocados(listaPartidos);
		
		escrita.imprimeDistribuicaoFaixaEtaria(listaPoliticos, dataEleicao, numVagas);
		escrita.imprimeDistribuicaoSexo(listaPoliticos, numVagas);
		escrita.imprimeNumVotos(totalVotos, votosLegenda, votosNominais);
	}
}
