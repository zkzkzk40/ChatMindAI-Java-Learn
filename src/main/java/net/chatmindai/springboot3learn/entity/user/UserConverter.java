package net.chatmindai.springboot3learn.entity.user;

import net.chatmindai.springboot3learn.entity.demo.dto.User.CreateUserDTO;
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
 * @author zk
 * @date 2024/10/09
 */
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
    User userDtoToUser(CreateUserDTO userDto);

    @Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }
}