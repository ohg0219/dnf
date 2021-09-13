package com.dnf.model.dto.itemDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemDetailDTO {
    public String itemId;
    public String itemName;
    public String itemRarity;
    public String itemType;
    public String itemTypeDetail;
    public int itemAvailableLevel;
    public String itemObtainInfo;
    public String itemExplain;
    public String itemExplainDetail;
    public String itemFlavorText;
    public Object setItemId;
    public Object setItemName;
    public List<ItemStatusDTO> itemStatus;
    public CardInfo cardInfo;

}
