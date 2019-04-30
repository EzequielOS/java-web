package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import dominio.Artista;

public class Instanciar {
	public static Artista artista(HttpServletRequest request) {
		Artista aux = new Artista();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s;

		s = request.getParameter("codArtista");
		if (s != null && !s.isEmpty()) {
			aux.setCodArtista(Integer.parseInt(s));
		}

		s = request.getParameter("nome");
		if (s != null && !s.isEmpty()) {
			aux.setNome(s);
		}

		s = request.getParameter("nacionalidade");
		if (s != null && !s.isEmpty()) {
			aux.setNacionalidade(s);
		}

		s = request.getParameter("cache");
		if (s != null && !s.isEmpty()) {
			aux.setCache(new BigDecimal(s));
		}

		try {
			s = request.getParameter("nascimento");
			if (s != null && !s.isEmpty()) {
				aux.setNascimento(sdf.parse(s));
			}
		} catch (Exception e) {
		}
		return aux;
	}

}
