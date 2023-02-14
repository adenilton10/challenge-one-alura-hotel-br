package TesteData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Teste2 {

	public static void main(String[] args) throws ParseException {

		String dataRecebida1 = "27/01/2023";
		String dataRecebida2 = "25/02/2023";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date data1 = formato.parse(dataRecebida1);
		Date data2 = formato.parse(dataRecebida2);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data1);
		calendar.set(calendar.MILLISECOND, 0);
		
		Date date = calendar.getTime();
		Date now = new Date();
		
		long timetamp = now.getTime() - date.getTime();
		long qtdias = TimeUnit.MICROSECONDS.toDays(timetamp);
		
		System.out.println(qtdias);
		
		
	}
	
}
