package br.com.molina.algamoney.algamoneyapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;



@Getter
@Setter
@Entity
@Table(name = "categoria")
public class CategoriaModel implements Serializable {
	 private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID codigo;
	@Column(nullable = false, unique = true, length = 30)
	private String nome;

}
