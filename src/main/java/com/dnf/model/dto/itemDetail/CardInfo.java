package com.dnf.model.dto.itemDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CardInfo {

    public List<Slot> slots;
    public List<Enchant> enchant;
}
