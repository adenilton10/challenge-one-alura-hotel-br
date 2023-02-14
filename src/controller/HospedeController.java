package controller;

import java.sql.Connection;
import java.util.List;

import dao.HospedeDAo;
import factory.ConnectionFactory;
import modelo.Hospede;

public class HospedeController {

	HospedeDAo hospedeDAo;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().RecuperaConexao();
		this.hospedeDAo = new HospedeDAo(connection);
	}
	
	public void salvar(Hospede hospede) {
		hospedeDAo.salvar(hospede);
	}

	public List<Hospede> listar(){
		return this.hospedeDAo.listar();
	}
	
	public List<Hospede> listarComFiltro(String sobrenome){
		return this.hospedeDAo.listarComFiltro(sobrenome);
	}
	
	public void editar(Hospede hospede) {
		this.hospedeDAo.editar(hospede);
	}

	public void deletar(Integer id) {
		this.hospedeDAo.deletar(id);
	}
	
}