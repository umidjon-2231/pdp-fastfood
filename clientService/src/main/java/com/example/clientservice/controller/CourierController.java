package com.example.clientservice.controller;

import com.example.clientservice.dto.ApiResponse;
import com.example.clientservice.dto.HumanDto;
import com.example.clientservice.entity.Attachment;
import com.example.clientservice.entity.Human;
import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.UserType;
import com.example.clientservice.mapper.HumanMapper;
import com.example.clientservice.repository.HumanRepository;
import com.example.clientservice.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courier")
public class CourierController {
    final HumanMapper humanMapper;
    final HumanRepository humanRepository;
    final HumanService humanService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(
                humanMapper.humanToHumanFrontDto(
                        humanRepository.findByUserTypeEqualsAndStatusIsNot(UserType.COURIER, ClientStatus.DELETED)
                )
        );
    }

    @PostMapping()
    public HttpEntity<?> add(@ModelAttribute HumanDto dto){
        ApiResponse<?> apiResponse = humanService.add(dto, UserType.COURIER);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@ModelAttribute HumanDto dto, @PathVariable Long id){
        ApiResponse<?> apiResponse = humanService.edit(id, dto, UserType.COURIER);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<?> apiResponse=humanService.delete(id, UserType.COURIER);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PostMapping("/{id}/block")
    public HttpEntity<?> block(@PathVariable Long id){
        ApiResponse<Object> apiResponse=humanService.block(id, UserType.COURIER);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @GetMapping("/{id}/photo")
    public HttpEntity<?> getPhoto(@PathVariable Long id){
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if(optionalHuman.isEmpty() || optionalHuman.get().getUserType()!=UserType.COURIER){
            return ResponseEntity.notFound().build();
        }
        Attachment photo = optionalHuman.get().getPhoto();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(photo.getType()))
                .contentLength(photo.getSize())
                .body(photo.getBytes());
    }
}