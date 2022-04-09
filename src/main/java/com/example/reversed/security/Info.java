package com.example.reversed.security;

import com.example.reversed.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Info {

    private int id;
    private String email;
    private ClientType clientType;
    private LocalDateTime issuedAt;
}
