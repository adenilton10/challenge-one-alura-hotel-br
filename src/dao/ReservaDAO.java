package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {

	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO RESERVAS (DATAENTRADA, DATASAIDA, VALOR, FORMAPAGAMENTO)" + "VALUES(?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setDate(1, reserva.getDataEntrada());
				pstm.setDate(2, reserva.getDataSaida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();

			String sql = "SELECT * FROM RESERVAS";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getDate(2), rst.getDate(3), rst.getString(4),
								rst.getString(5));
						reserva.setId(rst.getInt(1));

						reservas.add(reserva);
					}
					return reservas;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}	
	
	public List<Reserva> listarComFiltro(Integer id) {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM RESERVAS WHERE ID = ?";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setInt(1, id);
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) {
						Reserva reserva = new Reserva(rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
							reserva.setId(rst.getInt(1));
							
						reservas.add(reserva);	
					}
				}
			}	
			return reservas;	
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void editar(Reserva reserva) {
		
		String sql = "UPDATE RESERVAS SET DATAENTRADA = ?, DATASAIDA= ?, VALOR= ?, FORMAPAGAMENTO = ? WHERE ID = ?";
	
		try( PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setDate(1, reserva.getDataEntrada());
			pstm.setDate(2, reserva.getDataSaida());
			pstm.setString(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());
			pstm.setInt(5, reserva.getId());
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id)  {
	
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?")){
			pstm.setInt(1, id);
			pstm.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
