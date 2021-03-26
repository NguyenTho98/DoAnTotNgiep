package com.sapo.qlsc.service;

import com.sapo.qlsc.dto.DistrictDTO;
import com.sapo.qlsc.dto.WardDTO;

import java.util.List;

public interface WardService {

    List<WardDTO> getWardOfDistrict(String district);
}
