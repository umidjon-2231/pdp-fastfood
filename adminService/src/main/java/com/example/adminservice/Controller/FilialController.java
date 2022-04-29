package com.example.adminservice.Controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.service.FillialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filial")
public class FilialController {
    final FilialRepository filialRepository;
    final FillialService fillialService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody FilialDto filialDto){
        ApiResponse<Filial> add = fillialService.add(filialDto);
        return ResponseEntity.ok().body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit (@PathVariable Long id, @RequestBody FilialDto filialDto){
        ApiResponse<Filial> edit = fillialService.edit(id, filialDto);
        return ResponseEntity.ok().body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Filial> delete = fillialService.delete(id);
        return ResponseEntity.ok().body("deleted");
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Filial> byIdAAndActiveTrue = filialRepository.findByActiveTrue();
        return ResponseEntity.ok().body(byIdAAndActiveTrue);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(byId.get());
    }
}
