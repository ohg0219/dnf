package com.dnf.model.dto.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemListDTO {

    private String itemId;
    private String itemName;
    private String itemRarity;
    private String itemType;
    private String itemTypeDetail;
    private int itemAvailableLevel;

}