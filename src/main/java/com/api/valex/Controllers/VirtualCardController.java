package com.api.valex.Controllers;

import com.api.valex.Controllers.dto.VirtualCardCreateDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Services.VirtualCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/virtual")
public class VirtualCardController {
    @Autowired
    VirtualCardService virtualCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> CreateVirtualCard(@RequestBody VirtualCardCreateDto req) throws ErrorHandler404, ErrorHandler400 {
        virtualCardService.CreateVirtual(req);
        return ResponseEntity.ok().build();
    }
}
