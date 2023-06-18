package com.project.library.converters.reader;

import com.project.library.core.dto.ReaderInfo;
import com.project.library.entities.ReaderEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ReaderInfoToReaderEntityConverter implements Converter<ReaderInfo, ReaderEntity> {
    @Override
    public ReaderEntity convert(ReaderInfo source) {

        LocalDateTime now = LocalDateTime.now();

        return ReaderEntity.builder()
                .setUuid(UUID.randomUUID())
                .setName(source.getName())
                .setDateBirth(source.getDateBirth())
                .setAddress(source.getAddress())
                .setDateRegistration(now)
                .setDateUpdate(now)
                .build();
    }
}
