package com.ipartek.formacion.ejemplos.iparfood.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;

@Mapper(componentModel = "spring")
public interface PlatoMapper {
	Plato toEntity(PlatoDto dto);

	PlatoDto toDto(Plato entity);

	@Mapping(target = "id", ignore = true)
	@BeanMapping(ignoreByDefault = true)
	Plato toNewEntity(PlatoDto dto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(PlatoDto dto, @MappingTarget Plato entity);
}