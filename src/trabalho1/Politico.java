package trabalho1;

import java.time.LocalDate;

/**
 * 
 * A classe Politico tem como proposta armazenar os atributos de cada politico individualmente, incluindo:
 * 1) Seus dados pessoais, como nome, data de nascimento e sexo
 * 2) Seus dados eleitorais, como numero, votos nominais, situacao (eleito ou nao), nome na urna e partido
 * 
 */
public class Politico implements Comparable<Politico>{
	private int numero;
	private int votosNominais;
	private String situacao;
	private String nome;
	private String nomeUrna;
	private char sexo;
	private LocalDate dataNascimento;
	private int partido;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getVotosNominais() {
		return votosNominais;
	}

	public void setVotosNominais(int votos_nominais) {
		this.votosNominais = votos_nominais;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUrna() {
		return nomeUrna;
	}

	public void setNomeUrna(String nome_urna) {
		this.nomeUrna = nome_urna;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate data_nascimento) {
		this.dataNascimento = data_nascimento;
	}

	public int getPartido() {
		return partido;
	}
	
	public void setPartido(int partido) {
		this.partido = partido;
	}
	
	/**
	 * Realiza a comparacao entre dois politicos com base em seus votos nominais
	 * Como criterio de desempate, usa-se suas idades
	 * @param politico Politico a ser comparado
	 */
	@Override
	public int compareTo(Politico politico) {
		int diferenca = politico.getVotosNominais() - this.votosNominais;
		
		if(diferenca != 0) {
			return diferenca;
		}else {
			return this.dataNascimento.compareTo(politico.getDataNascimento());
		}
	}
} 
