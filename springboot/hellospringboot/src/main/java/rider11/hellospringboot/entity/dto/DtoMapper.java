package rider11.hellospringboot.entity.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import rider11.hellospringboot.entity.ErpOrder;

@Mapper
public interface DtoMapper {
    DtoMapper Instance = Mappers.getMapper(DtoMapper.class);

    ErpOrderDto mapOrderDto(ErpOrder order);
}
