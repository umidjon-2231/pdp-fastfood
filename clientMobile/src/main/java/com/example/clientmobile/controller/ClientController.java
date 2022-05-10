package com.example.clientmobile.controller;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.dto.HumanDto;
import com.example.clientmobile.entity.Attachment;
import com.example.clientmobile.entity.Human;
import com.example.clientmobile.entity.enums.ClientStatus;
import com.example.clientmobile.entity.enums.UserType;
import com.example.clientmobile.mapper.HumanMapper;
import com.example.clientmobile.repository.HumanRepository;
import com.example.clientmobile.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    final HumanMapper humanMapper;
    final HumanRepository humanRepository;
    final ClientService clientService;

//    @GetMapping
//    public HttpEntity<?> getAll() {
//        return ResponseEntity.ok().body(
//                humanMapper.humanToHumanFrontDto(
//                        humanRepository.findByUserTypeEqualsAndStatusIsNot(UserType.CLIENT, ClientStatus.DELETED)
//                )
//        );
//    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id) {
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if (optionalHuman.isEmpty() || optionalHuman.get().getUserType()!=UserType.CLIENT) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .message("Client with id=(" + id + ") not found")
                    .build());
        }
        return ResponseEntity.ok().body(humanMapper.humanToHumanFrontDto(optionalHuman.get()));
    }

    @PostMapping
    public HttpEntity<?> add(@ModelAttribute HumanDto dto) {
        ApiResponse<?> apiResponse = clientService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@ModelAttribute HumanDto dto, @PathVariable Long id) {
        ApiResponse<?> apiResponse = clientService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

//    TODO delete kerakmi?

    @PostMapping("/{id}/block")
    public HttpEntity<?> block(@PathVariable Long id) {
        ApiResponse<Object> apiResponse = clientService.block(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{id}/photo")
    public HttpEntity<?> getPhoto(@PathVariable Long id) {
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if (optionalHuman.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (optionalHuman.get().getPhoto() == null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/assets/image-not-found.png")).build();
        }
        Attachment photo = optionalHuman.get().getPhoto();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(photo.getType()))
                .contentLength(photo.getSize())
                .body(photo.getBytes());
    }
}
