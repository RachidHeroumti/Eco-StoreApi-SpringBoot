package com.demo.AuthConfigs.Responces;

import com.demo.AuthConfigs.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ResponceApi {
    private String message;
    private Object data;

    public ResponceApi(String message, Object data) {
        this.message=message;
        this.data =data;
    }
}
