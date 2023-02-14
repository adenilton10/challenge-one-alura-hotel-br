package modelo;

import java.sql.Date;

public class Hospede {

	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String nacionalidade;
	private String telefone;
	private String idReserva;
	private Integer idHopede;
	
	public Hospede(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone,
			String idReserva) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.idReserva = idReserva;
	}
	
	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getIdReserva() {
		return idReserva;
	}

	public void setIdHospede(Integer idhospede) {
		this.idHopede = idhospede;	
	}
	
	public Integer getIdHopede() {
		return idHopede;
	}
	
	
	
}
