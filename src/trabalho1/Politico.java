package trabalho1;

import java.time.LocalDate;

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

	public String getNome_urna() {
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
	
	@Override
	public String toString() {
		return this.numero + "," + 
				this.votosNominais + "," + 
				this.situacao + "," + 
				this.nome + "," + 
				this.nomeUrna + "," + 
				this.sexo + "," + 
				this.dataNascimento + "," + 
				this.partido;
	}

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
