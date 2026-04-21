package com.ipartek.formacion.ejemplos.iparfood.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ipartek.formacion.ejemplos.iparfood.dtos.UsuarioDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
	Usuario toEntity(UsuarioDto dto);

	UsuarioDto toDto(Usuario entity);

	@Mapping(target = "id", ignore = true)
	@BeanMapping(ignoreByDefault = true)
	Usuario toNewEntity(UsuarioDto dto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(UsuarioDto dto, @MappingTarget Usuario entity);
}