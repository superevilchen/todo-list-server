package com.example.reversed.beans.dtos;

import com.example.reversed.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResDto {

    private String email;
    private UUID token;
    private ClientType clientType;}
