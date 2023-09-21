package dao;

import java.sql.Connection;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Reserva;

public class TesteListarReserva {

	public static void main(String[] args) {
		
		Connection connection = new ConnectionFactory().RecuperaConexao();
		
		ReservaDAO reservaDAO = new ReservaDAO(connection);
		
		List<Reserva> listar = reservaDAO.listar();
		
		System.out.println(listar);
	
	}
	
}
