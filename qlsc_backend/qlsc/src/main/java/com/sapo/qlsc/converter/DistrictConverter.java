package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.DistrictDTO;
import com.sapo.qlsc.entity.District;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DistrictConverter {

    public DistrictDTO convertToDTO(District district){
        ModelMapper modelMapper = new ModelMapper();
        DistrictDTO districtDTO = modelMapper.map(district, DistrictDTO.class);
        return districtDTO;
    }

    public District convertToEntity(DistrictDTO districtDTO) {
        ModelMapper modelMapper = new ModelMapper();
        District district = modelMapper.map(districtDTO, District.class);
        return district;
    }
}
