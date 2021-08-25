package com.william.createwebservice.service.impl;

import com.william.createwebservice.io.repository.TableRepository;
import com.william.createwebservice.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;

public class TableServiceImpl implements TableService {
    @Autowired
    private TableRepository tableRepository;
}
