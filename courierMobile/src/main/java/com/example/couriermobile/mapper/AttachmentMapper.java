package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.AttachmentDto;
import com.example.couriermobile.entity.Attachment;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttachmentMapper {
    AttachmentDto toDto(Attachment file);
    @Mapping(source = "originalFilename", target = "name")
    @Mapping(source = "contentType", target = "type")
    Attachment toEntity(MultipartFile file) throws IOException;
}
