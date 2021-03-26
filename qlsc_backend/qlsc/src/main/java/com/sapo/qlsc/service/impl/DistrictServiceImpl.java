package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.DistrictConverter;
import com.sapo.qlsc.dto.DistrictDTO;
import com.sapo.qlsc.entity.District;
import com.sapo.qlsc.repository.DistrictRepository;
import com.sapo.qlsc.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final DistrictConverter districtConverter;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictConverter districtConverter) {
        this.districtRepository = districtRepository;
        this.districtConverter = districtConverter;
    }

    @Override
    public List<DistrictDTO> getDistricts() {

        List<DistrictDTO> districtDTOS = new ArrayList<>();
        List<District> districts = districtRepository.getDistinct();

        for (District district : districts){
            districtDTOS.add(districtConverter.convertToDTO(district));
        }
        return districtDTOS;
    }
}
