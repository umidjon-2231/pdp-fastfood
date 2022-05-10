package com.example.couriermobile.service;

import com.example.couriermobile.dto.ApiResponse;
import com.example.couriermobile.dto.CourierEditDto;
import com.example.couriermobile.dto.HumanFrontDto;
import com.example.couriermobile.entity.Attachment;
import com.example.couriermobile.entity.Human;
import com.example.couriermobile.entity.enums.UserType;
import com.example.couriermobile.mapper.AttachmentMapper;
import com.example.couriermobile.mapper.HumanMapper;
import com.example.couriermobile.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    final HumanRepository humanRepository;
    final HumanMapper humanMapper;
    final AttachmentMapper attachmentMapper;

    @SneakyThrows
    public ApiResponse<HumanFrontDto> changePhoto(Long id, MultipartFile photo){
        Optional<Human> optionalHuman = humanRepository.findById(id);
        if(optionalHuman.isEmpty()){
            return ApiResponse.<HumanFrontDto>builder()
                    .message("Courier with id=(" + id+") not found")
                    .build();
        }
        optionalHuman.get().setPhoto(attachmentMapper.toEntity(photo));
        Human save = humanRepository.save(optionalHuman.get());
        return ApiResponse.<HumanFrontDto>builder()
                .success(true)
                .message("Photo changed!")
                .data(humanMapper.toDto(save))
                .build();
    }

    public ApiResponse<HumanFrontDto> edit(Long id, CourierEditDto dto) {
        Optional<Human> optionalHuman = humanRepository.findById(id);
        if(optionalHuman.isEmpty() || optionalHuman.get().getUserType()!= UserType.COURIER){
            return ApiResponse.<HumanFrontDto>builder()
                    .message("Courier with id=(" + id+") not found")
                    .build();
        }
        Human human = optionalHuman.get();
        human.setName(dto.getName());
        human.setNumber(dto.getNumber());
        humanRepository.save(human);
        return ApiResponse.<HumanFrontDto>builder()
                .success(true)
                .message("Edited!")
                .build();
    }
}
