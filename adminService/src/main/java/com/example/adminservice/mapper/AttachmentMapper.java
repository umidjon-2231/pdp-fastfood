package com.example.adminservice.mapper;

import com.example.adminservice.dto.AttachmentDto;
import com.example.adminservice.entity.Attachment;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttachmentMapper {
    @Mapping(source = "originalFilename", target = "name")
    @Mapping(source = "contentType", target = "type")
    Attachment toEntity(MultipartFile file) throws IOException;

    AttachmentDto toDto(Attachment file);

    @Mapping(source = "originalFilename", target = "name")
    @Mapping(source = "contentType", target = "type")
    void update(MultipartFile file, @MappingTarget Attachment attachment) throws IOException;
}
