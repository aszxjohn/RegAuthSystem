package com.example.RegAuthSystem.mapper;

import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.example.orm.entity.ClientInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-22T16:02:00+0800",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ClientInfoMapperImpl implements ClientInfoMapper {

    @Override
    public ClientInfoDto toDto(ClientInfo entity) {
        if ( entity == null ) {
            return null;
        }

        ClientInfoDto clientInfoDto = new ClientInfoDto();

        clientInfoDto.setClientAddress( entity.getClientAddress() );
        clientInfoDto.setClientInfoId( entity.getClientInfoId() );
        clientInfoDto.setIdentificationNumber( entity.getIdentificationNumber() );
        clientInfoDto.setPhoneNumbe( entity.getPhoneNumbe() );

        return clientInfoDto;
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

    @Override
    public ClientInfo toEntity(ClientInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ClientInfo clientInfo = new ClientInfo();

        clientInfo.setClientAddress( dto.getClientAddress() );
        clientInfo.setClientInfoId( dto.getClientInfoId() );
        clientInfo.setIdentificationNumber( dto.getIdentificationNumber() );
        clientInfo.setPhoneNumbe( dto.getPhoneNumbe() );

        return clientInfo;
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
}
