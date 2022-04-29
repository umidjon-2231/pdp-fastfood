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
public class FillialService {
final FilialRepository filialRepository;
final FilialMapper filialMapper;
    public ApiResponse<Filial> add(FilialDto filialDto){
        Filial filial = filialMapper.toEntity(filialDto);
        filialRepository.save(filial);
        return ApiResponse.<Filial>builder().message("added").success(true).obj(filial).build();
    }
    public ApiResponse<Filial> edit(Long id,FilialDto filialDto){
        Filial filial = filialMapper.toEntity(filialDto);
        filial.setId(id);
        filialRepository.save(filial);
        return ApiResponse.<Filial>builder().message("edited").success(true).obj(filial).build();
    }
    public ApiResponse<Filial> delete(Long id){
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()){
            return ApiResponse.<Filial>builder().message("not found").build();
        }
        byId.get().setActive(false);
        filialRepository.save(byId.get());
        return ApiResponse.<Filial>builder().message("deleted").success(true).build();
    }

}
