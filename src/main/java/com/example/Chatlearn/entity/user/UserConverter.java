package com.example.Chatlearn.entity.user;

import com.example.Chatlearn.entity.demo.dto.DemoDto;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "updateAt", ignore = true),
            @Mapping(target = "agreeTerms", expression = "java(userDto.isAgreeTerms() ? 1 : 0)"),
            @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "localDateToLocalDateTime"),
            @Mapping(target = "planDate", source = "planDate", qualifiedByName = "localDateToLocalDateTime")
    })
    User userDtoToUser(DemoDto userDto);

    @Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }
}
