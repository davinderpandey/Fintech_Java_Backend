package com.fintech.mapper;

import com.fintech.auth.Role;
import com.fintech.dto.UserDTO;
import com.fintech.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-05T16:21:24+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDTO mapToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.about( user.getAbout() );
        userDTO.email( user.getEmail() );
        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.password( user.getPassword() );
        if ( user.getRole() != null ) {
            userDTO.role( user.getRole().name() );
        }
        userDTO.userId( user.getUserId() );

        return userDTO.build();
    }

    @Override
    public User mapToUser(UserDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.about( userDto.getAbout() );
        user.email( userDto.getEmail() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.password( userDto.getPassword() );
        if ( userDto.getRole() != null ) {
            user.role( Enum.valueOf( Role.class, userDto.getRole() ) );
        }
        user.userId( userDto.getUserId() );

        return user.build();
    }

    @Override
    public List<UserDTO> mapToUserDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( mapToUserDto( user ) );
        }

        return list;
    }
}
