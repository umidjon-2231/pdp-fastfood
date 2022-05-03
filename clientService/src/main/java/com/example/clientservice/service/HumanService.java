package com.example.clientservice.service;

import com.example.clientservice.dto.ApiResponse;
import com.example.clientservice.dto.HumanDto;
import com.example.clientservice.dto.HumanFrontDto;
import com.example.clientservice.entity.Human;
import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.UserType;
import com.example.clientservice.mapper.HumanMapper;
import com.example.clientservice.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HumanService {
    final HumanRepository humanRepository;
    final HumanMapper humanMapper;

    public ApiResponse<HumanFrontDto> add(HumanDto dto, UserType userType) {
        Human human = humanMapper.humanDtoToHuman(dto);
        human.setUserType(userType);
        if (dto.getStatus() == null) {
            human.setStatus(ClientStatus.ACTIVE);
        }
        Human save = humanRepository.save(human);
        return ApiResponse.<HumanFrontDto>builder()
                .data(humanMapper.humanToHumanFrontDto(save))
                .success(true)
                .message("Added!")
                .build();
    }

    public ApiResponse<HumanFrontDto> edit(Long id, HumanDto dto, UserType userType) {
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if (optionalHuman.isEmpty() || optionalHuman.get().getUserType() != userType) {
            return ApiResponse.<HumanFrontDto>builder()
                    .message(userType.name() + " with id=(" + id + ") not found")
                    .build();
        }
        Human human = optionalHuman.get();
        humanMapper.updateHumanFromHumanDto(dto, human);
        if (dto.getStatus() == null) {
            human.setStatus(ClientStatus.ACTIVE);
        }
        Human save = humanRepository.save(human);
        return ApiResponse.<HumanFrontDto>builder()
                .data(humanMapper.humanToHumanFrontDto(save))
                .success(true)
                .message("Edited!")
                .build();
    }

    public ApiResponse<?> delete(Long id, UserType userType) {
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if (optionalHuman.isEmpty() || optionalHuman.get().getUserType() != userType) {
            return ApiResponse.builder()
                    .message(userType.name() + " with id=(" + id + ") not found")
                    .build();
        }
        optionalHuman.get().setStatus(ClientStatus.DELETED);
        humanRepository.save(optionalHuman.get());
        return ApiResponse.builder()
                .success(true)
                .message("Deleted!")
                .build();
    }

    public ApiResponse<Object> block(Long id, UserType userType) {
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if (optionalHuman.isEmpty() || optionalHuman.get().getUserType() != userType) {
            return ApiResponse.builder()
                    .message(userType.name() + " with id=(" + id + ") not found")
                    .build();
        }
        optionalHuman.get().setStatus(ClientStatus.BLOCKED);
        humanRepository.save(optionalHuman.get());
        return ApiResponse.builder()
                .success(true)
                .message("Blocked!")
                .build();
    }
}
