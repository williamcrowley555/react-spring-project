package com.william.createwebservice.service;

import com.william.createwebservice.io.entity.TableEntity;
import com.william.createwebservice.shared.dto.TableDTO;
import org.springframework.stereotype.Service;

@Service
public interface TableService {
    TableDTO getTableById(Long id);
    TableDTO getTableByStatus(Boolean status);
    TableDTO getTableBySeat(int seat);
    TableEntity save(TableDTO tableDTO);

}
