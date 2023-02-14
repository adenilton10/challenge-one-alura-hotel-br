package TesteData;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teste3 {

	public static void main(String[] args) throws ParseException {
		
		String dataRecebida1 = "11/01/2024";
		String dataRecebida2 = "30/12/2023";
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate data1 = LocalDate.parse(dataRecebida1, formato); 
		LocalDate data2 = LocalDate.parse(dataRecebida2, formato); 
		
		int diasDoAnodata1 =  data1.getDayOfYear();
		int diasDoAnodata2 =  data2.getDayOfYear();
		
		if(data2.getYear() < data1.getYear()) {
			diasDoAnodata1 = diasDoAnodata1 + data2.lengthOfYear();
		}
		
		int resul = diasDoAnodata1 - diasDoAnodata2;
		
		System.out.println(diasDoAnodata1+ "/" +diasDoAnodata2);
		System.out.println(resul);	
	}
	
}
