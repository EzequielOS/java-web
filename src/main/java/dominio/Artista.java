package dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Artista {
	private Integer codArtista;
	private String nome;
	private String nacionalidade;
	private BigDecimal cache;
	private Date nascimento;
	
	private List<Participacao> participacoes;
	
}
