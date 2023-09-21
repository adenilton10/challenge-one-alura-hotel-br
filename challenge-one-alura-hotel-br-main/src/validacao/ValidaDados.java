package validacao;

import javax.swing.JOptionPane;

public class ValidaDados {
	
	public boolean validaString(String s, String nomeCampo) {
		
		if (s.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo: " + nomeCampo + " esta vazio.");
			return false;
		}else {			
			return true;
		}
		
	}

	public boolean validaInteger(String s, String nomeCampo) {
		
		if (s.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo: " + nomeCampo + " esta vazio.");
			return false;
		} else {
			try {
				Integer.valueOf(s);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Campo: " + nomeCampo + " precisa ser numero.");
				return false;
			}
		}
		return true;
	}

	public boolean validaDate(String s, String nomeCampo) {
		if(s.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo: " + nomeCampo + " esta vazio.");
			return false;
		}else {
			try {				
				java.sql.Date.valueOf(s);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Campo: " + nomeCampo + " precissa esta no formato: yyyy-MM-dd");
				return false;
			} 
		}
		return true;
	}
}
