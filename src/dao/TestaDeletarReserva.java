package dao;

import java.sql.Connection;

import factory.ConnectionFactory;

public class TestaDeletarReserva {

	public static void main(String[] args) {
		
		Connection connection = new ConnectionFactory().RecuperaConexao();
		
		ReservaDAO reservaDAO = new ReservaDAO(connection);
		
		reservaDAO.deletar(9);

	}

}
