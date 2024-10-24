package io.github.sugarmgp.springboot3learn.entity.user;

import io.github.sugarmgp.springboot3learn.entity.user.dto.CreateUserDTO;
import io.github.sugarmgp.springboot3learn.entity.user.dto.UpdateUserDTO;
import org.mapstruct.Mapper;
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
    })
    User userDtoToUser(CreateUserDTO createUserDTO);

    User userDtoToUser(UpdateUserDTO updateUserDTO);

    @Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }
}