package com.example.orderservice.mapper;

import com.example.orderservice.dto.AttachmentDto;
import com.example.orderservice.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttachmentMapper {
    AttachmentDto toDto(Attachment file);
}
