package com.example.RegAuthSystem.mapper;

import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.orm.entity.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T00:15:45+0800",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto toDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setCreateDate( entity.getCreateDate() );
        clientDto.setCreateUser( entity.getCreateUser() );
        clientDto.setUpdateDate( entity.getUpdateDate() );
        clientDto.setUpdateUser( entity.getUpdateUser() );
        clientDto.setClientId( entity.getClientId() );
        clientDto.setClientInfoId( entity.getClientInfoId() );
        clientDto.setCustomerNumber( entity.getCustomerNumber() );
        clientDto.setEmail( entity.getEmail() );
        clientDto.setEnableTwoFactor( entity.getEnableTwoFactor() );
        clientDto.setIsLock( entity.getIsLock() );
        clientDto.setIsLockedTime( entity.getIsLockedTime() );
        clientDto.setLoginFailCount( entity.getLoginFailCount() );
        clientDto.setLoginSuccessDate( entity.getLoginSuccessDate() );
        clientDto.setPassword( entity.getPassword() );
        clientDto.setRegisterReviewId( entity.getRegisterReviewId() );
        clientDto.setRegistrationProgressVerificationCode( entity.getRegistrationProgressVerificationCode() );
        clientDto.setRegistrationProgressVerificationCodeExpiryTime( entity.getRegistrationProgressVerificationCodeExpiryTime() );
        clientDto.setRegistrationVerificationCode( entity.getRegistrationVerificationCode() );
        clientDto.setRegistrationVerificationCodeExpiryTime( entity.getRegistrationVerificationCodeExpiryTime() );
        clientDto.setResetPasswordVerificationCodeEmail( entity.getResetPasswordVerificationCodeEmail() );
        clientDto.setResetPasswordVerificationCodeExpiryTime( entity.getResetPasswordVerificationCodeExpiryTime() );
        clientDto.setSetPasswordVerificationCodeEmail( entity.getSetPasswordVerificationCodeEmail() );
        clientDto.setSetPasswordVerificationCodeExpiryTime( entity.getSetPasswordVerificationCodeExpiryTime() );
        clientDto.setStatus( entity.getStatus() );
        clientDto.setTwoFactorCode( entity.getTwoFactorCode() );
        clientDto.setTwoFactorCodeExpiryTime( entity.getTwoFactorCodeExpiryTime() );

        return clientDto;
    }

    @Override
    public List<ClientDto> toDto(List<Client> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ClientDto> list = new ArrayList<ClientDto>( entityList.size() );
        for ( Client client : entityList ) {
            list.add( toDto( client ) );
        }

        return list;
    }

    @Override
    public Client toEntity(ClientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setCreateDate( dto.getCreateDate() );
        client.setCreateUser( dto.getCreateUser() );
        client.setUpdateDate( dto.getUpdateDate() );
        client.setUpdateUser( dto.getUpdateUser() );
        client.setClientId( dto.getClientId() );
        client.setClientInfoId( dto.getClientInfoId() );
        client.setCustomerNumber( dto.getCustomerNumber() );
        client.setEmail( dto.getEmail() );
        client.setEnableTwoFactor( dto.getEnableTwoFactor() );
        client.setIsLock( dto.getIsLock() );
        client.setIsLockedTime( dto.getIsLockedTime() );
        client.setLoginFailCount( dto.getLoginFailCount() );
        client.setLoginSuccessDate( dto.getLoginSuccessDate() );
        client.setPassword( dto.getPassword() );
        client.setRegisterReviewId( dto.getRegisterReviewId() );
        client.setRegistrationProgressVerificationCode( dto.getRegistrationProgressVerificationCode() );
        client.setRegistrationProgressVerificationCodeExpiryTime( dto.getRegistrationProgressVerificationCodeExpiryTime() );
        client.setRegistrationVerificationCode( dto.getRegistrationVerificationCode() );
        client.setRegistrationVerificationCodeExpiryTime( dto.getRegistrationVerificationCodeExpiryTime() );
        client.setResetPasswordVerificationCodeEmail( dto.getResetPasswordVerificationCodeEmail() );
        client.setResetPasswordVerificationCodeExpiryTime( dto.getResetPasswordVerificationCodeExpiryTime() );
        client.setSetPasswordVerificationCodeEmail( dto.getSetPasswordVerificationCodeEmail() );
        client.setSetPasswordVerificationCodeExpiryTime( dto.getSetPasswordVerificationCodeExpiryTime() );
        client.setStatus( dto.getStatus() );
        client.setTwoFactorCode( dto.getTwoFactorCode() );
        client.setTwoFactorCodeExpiryTime( dto.getTwoFactorCodeExpiryTime() );

        return client;
    }

    @Override
    public List<Client> toEntity(List<ClientDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( dtoList.size() );
        for ( ClientDto clientDto : dtoList ) {
            list.add( toEntity( clientDto ) );
        }

        return list;
    }
}
