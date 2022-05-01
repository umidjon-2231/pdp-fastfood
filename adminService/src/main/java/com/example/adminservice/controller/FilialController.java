package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/filial")
@RequiredArgsConstructor
public class FilialController {
    final FilialService filialService;
    final FilialRepository filialRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(filialRepository.findByActiveTrue());
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Filial> optionalFilial = filialRepository.findByIdAndActiveTrue(id);
        if(optionalFilial.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalFilial.get());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody FilialDto dto){
        ApiResponse<?> apiResponse = filialService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody FilialDto dto, @PathVariable Long id){
        ApiResponse<?> apiResponse = filialService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<?> apiResponse = filialService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
