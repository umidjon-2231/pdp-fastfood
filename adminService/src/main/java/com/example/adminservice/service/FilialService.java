package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.mapper.FilialMapper;
import com.example.adminservice.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialService {
    final FilialRepository filialRepository;
    final FilialMapper filialMapper;

    public ApiResponse<Filial> add(FilialDto dto){
        Filial filial = filialMapper.filialDtoToFilial(dto);
        Filial save = filialRepository.save(filial);
        return ApiResponse.<Filial>builder()
                .data(save)
                .success(true)
                .message("Added!")
                .build();
    }

    public ApiResponse<Filial> edit(Long id, FilialDto dto){
        Optional<Filial> optionalFilial = filialRepository.findByIdAndActiveTrue(id);
        if(optionalFilial.isEmpty()){
            return ApiResponse.<Filial>builder()
                    .message("Filial with id=("+ id + ") not found")
                    .build();
        }
        Filial filial = optionalFilial.get();
        filialMapper.updateFilialFromFilialDto(dto, filial);
        Filial save = filialRepository.save(filial);
        return ApiResponse.<Filial>builder()
                .success(true)
                .message("Edited!")
                .data(save)
                .build();
    }


    public ApiResponse<Object> delete(Long id){
        Optional<Filial> optionalFilial = filialRepository.findByIdAndActiveTrue(id);
        if(optionalFilial.isEmpty()){
            return ApiResponse.builder()
                    .message("Filial with id=(" + id+") not found")
                    .build();
        }
        return ApiResponse.builder()
                .success(true)
                .message("Deleted!")
                .build();
    }
}
