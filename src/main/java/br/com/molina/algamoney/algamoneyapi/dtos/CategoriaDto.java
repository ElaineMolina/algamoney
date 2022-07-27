package br.com.molina.algamoney.algamoneyapi.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDto {
	
	@NotBlank
	private String nome;

}
