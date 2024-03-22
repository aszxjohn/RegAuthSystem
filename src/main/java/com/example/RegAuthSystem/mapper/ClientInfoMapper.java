package com.example.RegAuthSystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.example.orm.base.BaseMapper;
import com.example.orm.entity.ClientInfo;


/**
 * 注意：
 * 1. MapperStruct 是自動生產，需要在pom.xml
 * <configuration>
 * 	<source>1.8</source>
 * 	<target>1.8</target>
 * 	<annotationProcessorPaths>
 * 		<path>
 * 			<groupId>org.mapstruct</groupId>
 * 			<artifactId>mapstruct-processor</artifactId>
 * 		</path>
 * 	</annotationProcessorPaths>
 * </configuration>
 * 2. 新增Mapper，需要重新執行maven clean；maven install
 * 3. 可檢查target/generated-sources/annotations/... 是否有生成
 *
 * @author Timmy
 * @since 2022-09-16
 * */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientInfoMapper extends BaseMapper<ClientInfoDto, ClientInfo> {


	
}
