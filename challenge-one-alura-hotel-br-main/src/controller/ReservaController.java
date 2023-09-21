package controller;

import java.sql.Connection;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection = new ConnectionFactory().RecuperaConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}

	public void salvar(Reserva reserva) {
		reservaDAO.salvar(reserva);
	}
	
	public List<Reserva> listar(){
		return this.reservaDAO.listar();
	}
 	
	public List<Reserva> listaComFiltro(Integer id){
		 return this.reservaDAO.listarComFiltro(id);
	}
	
	public void editar(Reserva reserva) {
		this.reservaDAO.editar(reserva);
	}
	
	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}
}
