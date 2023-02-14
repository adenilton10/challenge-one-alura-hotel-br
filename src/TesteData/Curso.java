package TesteData;

import java.sql.Date;

public class Curso {

	private String nome;
	private Date dataDeLancamento;

	public void setLancamento(Date data) {
		this.dataDeLancamento = data;
	}

	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}
}