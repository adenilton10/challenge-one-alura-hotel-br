package TesteData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Teste {

	public static void main(String[] args) throws ParseException {

		Curso curso = new Curso();
		
		String dataRecebida1 = "11/01/2025";
		String dataRecebida2 = "30/12/2024";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date data1 = formato.parse(dataRecebida1);
		Date data2 = formato.parse(dataRecebida2);
		
		Integer sub = (data2.getDay() - data1.getDay()) * 20 ;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data2);
		
		
		//System.out.println(formato.format(calendar.getTime()));

		//System.out.println(sub);
	
	
	}

}
