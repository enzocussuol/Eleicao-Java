package trabalho1;

import java.time.LocalDate;

public class Politico {
	private int numero;
	private int votosNominais;
	private String situacao;
	private String nome;
	private String nomeUrna;
	private char sexo;

	private LocalDate dataNascimento; //verificar como funciona date
	private String destinoVoto;
	private int partido;
	
	public Politico() {
		
	}
	
	public Politico(int numero, int votos_nominais, String situacao, String nome, String nome_urna, char sexo, LocalDate data_nascimento ,String destino_voto, int partido) {
		this.setNumero(numero);
		this.setVotosNominais(votos_nominais);
		this.setSituacao(situacao);
		this.setNome(nome);
		this.setNomeUrna(nome_urna);
		this.setSexo(sexo);
		this.dataNascimento = data_nascimento;
		this.setDestinoVoto(destino_voto);

		this.setPartido(partido);
	}
	
	public void apresentarPolitico() {
		//imprimir politico
	}

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

	public String getDestinoVoto() {
		return destinoVoto;
	}

	public void setDestinoVoto(String destino_voto) {
		this.destinoVoto = destino_voto;
	}

	public int getPartido() {
		return partido;
	}

	public void setPartido(int partido) {
		this.partido = partido;
	}
	
	
} 
