package com.ipartek.formacion.ejemplos.iparfood.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoDto;
import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoPostDto;
import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoPutDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;

@Mapper(componentModel = "spring")
public interface PlatoMapper {
	Plato toEntity(PlatoDto dto);
	
	@Mapping(target = "tipoComida.id", source = "tipoComidaId")
	Plato toEntity(PlatoPutDto dto);

	PlatoDto toDto(Plato entity);

	@Mapping(target = "id", ignore = true)
	Plato toNewEntity(PlatoDto dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "tipoComida.id", source = "tipoComidaId")
	Plato toNewEntity(PlatoPostDto dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(PlatoDto dto, @MappingTarget Plato entity);
}