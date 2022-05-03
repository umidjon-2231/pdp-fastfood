package com.example.clientmobile.service;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.dto.HumanDto;
import com.example.clientmobile.entity.Human;
import com.example.clientmobile.mapper.HumanMapper;
import com.example.clientmobile.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    final HumanRepository humanRepository;
    final HumanMapper humanMapper;

    public ApiResponse<?> add(HumanDto dto){
        Human human = humanMapper.humanDtoToHuman(dto);
        Human save = humanRepository.save(human);
        return ApiResponse.builder()
                .data(save)
                .success(true)
                .message("Added!")
                .build();
    }
}
