package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
//    source compras producto, se usa la primary key y luego si se llega al id producto
//    target Purchase item en el dominio
//    para total como se llaman igual se puede quitar ese mapeo
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productid"),
            @Mapping(source = "cantidad", target = "quantity"),
//            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

//    se hace el mapeo inversamente, pero se deben ignorar los que no se mapearon de
//    compras producto
    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "compra", ignore = true),
        @Mapping(target = "producto", ignore = true),
        @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
