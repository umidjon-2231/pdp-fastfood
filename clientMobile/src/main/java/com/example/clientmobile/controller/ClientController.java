package com.example.clientmobile.controller;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.dto.HumanDto;
import com.example.clientmobile.entity.enums.UserType;
import com.example.clientmobile.mapper.HumanMapper;
import com.example.clientmobile.repository.HumanRepository;
import com.example.clientmobile.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    final HumanMapper humanMapper;
    final HumanRepository humanRepository;
    final ClientService clientService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(
                humanMapper.humanToHumanFrontDto(
                        humanRepository.findByUserTypeEquals(UserType.CLIENT)
                )
        );
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody HumanDto dto){
        ApiResponse<?> apiResponse = clientService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
