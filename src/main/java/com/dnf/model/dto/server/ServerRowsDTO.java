package com.dnf.model.dto.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ServerRowsDTO {

    List<ServerListDTO> rows;

}
