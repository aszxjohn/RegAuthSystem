package com.example.RegAuthSystem.mapper;

import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.example.orm.entity.ClientInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T11:37:56+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ClientInfoMapperImpl implements ClientInfoMapper {

    @Override
    public ClientInfo toEntity(ClientInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ClientInfo clientInfo = new ClientInfo();

        clientInfo.setCreateUser( dto.getCreateUser() );
        clientInfo.setUpdateUser( dto.getUpdateUser() );
        clientInfo.setCreateDate( dto.getCreateDate() );
        clientInfo.setUpdateDate( dto.getUpdateDate() );
        clientInfo.setClientInfoId( dto.getClientInfoId() );
        clientInfo.setClientAddress( dto.getClientAddress() );
        clientInfo.setIdentificationNumber( dto.getIdentificationNumber() );
        clientInfo.setPhoneNumbe( dto.getPhoneNumbe() );

        return clientInfo;
    }

    @Override
    public ClientInfoDto toDto(ClientInfo entity) {
        if ( entity == null ) {
            return null;
        }

        ClientInfoDto clientInfoDto = new ClientInfoDto();

        clientInfoDto.setCreateUser( entity.getCreateUser() );
        clientInfoDto.setUpdateUser( entity.getUpdateUser() );
        clientInfoDto.setCreateDate( entity.getCreateDate() );
        clientInfoDto.setUpdateDate( entity.getUpdateDate() );
        if ( entity.getClientInfoId() != null ) {
            clientInfoDto.setClientInfoId( entity.getClientInfoId() );
        }
        clientInfoDto.setClientAddress( entity.getClientAddress() );
        clientInfoDto.setIdentificationNumber( entity.getIdentificationNumber() );
        clientInfoDto.setPhoneNumbe( entity.getPhoneNumbe() );

        return clientInfoDto;
    }

    @Override
    public List<ClientInfo> toEntity(List<ClientInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ClientInfo> list = new ArrayList<ClientInfo>( dtoList.size() );
        for ( ClientInfoDto clientInfoDto : dtoList ) {
            list.add( toEntity( clientInfoDto ) );
        }

        return list;
    }

    @Override
    public List<ClientInfoDto> toDto(List<ClientInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ClientInfoDto> list = new ArrayList<ClientInfoDto>( entityList.size() );
        for ( ClientInfo clientInfo : entityList ) {
            list.add( toDto( clientInfo ) );
        }

        return list;
    }
}
