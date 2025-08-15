package vn.ducthe.service;

import vn.ducthe.dto.response.AttributeOptionDTO;

import java.util.List;

public interface AttributeService {
    List<AttributeOptionDTO> getAttributes();
}
