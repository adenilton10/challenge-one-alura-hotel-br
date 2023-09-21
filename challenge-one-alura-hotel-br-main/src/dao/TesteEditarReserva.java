package dao;

import java.sql.Connection;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Hospede;
import modelo.Reserva;

public class TesteEditarReserva {

	public static void main(String[] args) {
		
		Connection connection = new ConnectionFactory().RecuperaConexao();
		
		ReservaDAO reservaDAO = new ReservaDAO(connection);
		
		String dataEntrada = "2023-01-20";
		String dataSaida = "2023-01-22";
	
		Reserva reserva = new Reserva(java.sql.Date.valueOf("2023-01-25"), java.sql.Date.valueOf("2023-01-30"), "R$40", "Dinheiro"); 
		reserva.setId(1);
		reservaDAO.editar(reserva);
		
		
		
	}
	
}
