package TesteData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teste4 {

	

	public static void main(String[] args) {
		
		String dataRecebida1 = "11/01/2024";
		String dataRecebida2 = "30/12/2023";
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate lDdataEntrada = LocalDate.parse(dataRecebida2, formato);
		LocalDate lDdataSaida = LocalDate.parse(dataRecebida1, formato);
		
		int diasDoAnoDataEntrada = lDdataEntrada.getDayOfYear();
		int diasDoAnoDataSaida = lDdataSaida.getDayOfYear();
		
		if(lDdataEntrada.getYear() < lDdataSaida.getYear()) {
			diasDoAnoDataSaida = diasDoAnoDataSaida + lDdataEntrada.lengthOfYear();
		}
		
		int resultado = (diasDoAnoDataSaida - diasDoAnoDataEntrada);
		
		System.out.println(lDdataSaida.getDayOfYear() + "/" + lDdataEntrada.getDayOfYear() );
		System.out.println(resultado);
		
		
	}
	
}
