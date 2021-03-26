package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.WardConverter;
import com.sapo.qlsc.dto.WardDTO;
import com.sapo.qlsc.entity.Ward;
import com.sapo.qlsc.repository.WardRepository;
import com.sapo.qlsc.service.WardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    private final WardConverter wardConverter;

    public WardServiceImpl(WardRepository wardRepository, WardConverter wardConverter) {
        this.wardRepository = wardRepository;
        this.wardConverter = wardConverter;
    }

    @Override
    public List<WardDTO> getWardOfDistrict(String district) {

        List<WardDTO> wardDTOS = new ArrayList<>();
        List<Ward> wards = wardRepository.getWardByDistrict(district);

        for (Ward ward : wards){
            wardDTOS.add(wardConverter.convertToDTO(ward));
        }
        return wardDTOS;
    }
}
