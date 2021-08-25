package com.william.createwebservice.service.impl;

import com.william.createwebservice.exception.TableServiceException;
import com.william.createwebservice.io.entity.TableEntity;
import com.william.createwebservice.io.repository.TableRepository;
import com.william.createwebservice.service.TableService;
import com.william.createwebservice.shared.dto.TableDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableRepository tableRepository;


    @Override
    public TableDTO getTableById(Long id) {
        Optional<TableEntity> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new TableServiceException("Id is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        TableEntity tableEntity = optional.get();
        TableDTO returnValue = modelMapper.map(tableEntity, TableDTO.class);
        return returnValue;
    }

    @Override
    public TableDTO getTableByStatus(Boolean status) {
        Optional<TableEntity> optional = tableRepository.findByStatus(status);
        if (optional.isEmpty()) {
            throw new TableServiceException("Id is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        TableEntity tableEntity = optional.get();
        TableDTO returnValue = modelMapper.map(tableEntity, TableDTO.class);
        return returnValue;
    }

    @Override
    public TableDTO getTableBySeat(int seat) {
        Optional<TableEntity> optional = tableRepository.findBySeat(seat);
        if (optional.isEmpty()) {
            throw new TableServiceException("Id is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        TableEntity tableEntity = optional.get();
        TableDTO returnValue = modelMapper.map(tableEntity, TableDTO.class);
        return returnValue;
    }

    @Override
    public TableEntity save(TableDTO tableDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TableEntity tableEntity = modelMapper.map(tableDTO, TableEntity.class);
        return tableRepository.save(tableEntity);
    }
}
