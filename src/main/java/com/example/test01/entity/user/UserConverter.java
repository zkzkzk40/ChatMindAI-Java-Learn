package com.example.test01.entity.user;

import com.example.test01.entity.Dto.User.CreateUserDTO;
import com.example.test01.entity.Dto.User.UpdateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户转换器
 *
 * @author MangoGovo
 * @date 2024/10/09
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "updateAt", ignore = true),
    })
    User userDtoToUser(CreateUserDTO userDto);
    User userDtoToUser(UpdateUserDTO userDto);
    @Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }
}