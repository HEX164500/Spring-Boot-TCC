package com.sigen.api.dto;

import com.sigen.api.enums.NivelDeAcesso;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdentityControllerDTO {

	private String username;
	private NivelDeAcesso acesso;
}
