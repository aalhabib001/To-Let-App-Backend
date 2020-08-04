//package com.toletproject.ToLetProject.service;
//
//import com.toletproject.ToLetProject.dto.TempDTO;
//import com.toletproject.ToLetProject.dto.TempDTOList;
//import com.toletproject.ToLetProject.model.UpzilaModels;
//import com.toletproject.ToLetProject.repository.UpzilaRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//
//
//@Service
//public class TempService {
//
//    UpzilaRepository upzilaRepository;
//
//    public String save(TempDTOList tempDTOList) {
//        int id = 800;
//        for (TempDTO tempDTO : tempDTOList.getModule()) {
//            UpzilaModels upzilaModels = new UpzilaModels();
//
//
//            upzilaModels.setId(id);
//            upzilaModels.setName(tempDTO.getName());
//            upzilaModels.setDistrictId(72);
//            System.out.println(upzilaModels.getName());
//            upzilaRepository.save(upzilaModels);
//            id++;
//
//        }
//
//        return "ok";
//    }
//}
