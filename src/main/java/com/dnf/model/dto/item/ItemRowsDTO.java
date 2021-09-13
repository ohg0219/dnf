package com.dnf.model.dto.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemRowsDTO {

    List<ItemListDTO> rows;

}
