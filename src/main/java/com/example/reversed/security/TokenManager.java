package com.example.reversed.security;

import com.example.reversed.beans.ClientType;
import com.example.reversed.exceptions.TaskException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.reversed.exceptions.ExceptionState.INVALID_TOKEN;

@Service
public class TokenManager {

    private Map<UUID, Info> tokens = new HashMap<>();

    public void deleteUserTokens(int id){
        tokens.entrySet().removeIf(token -> token.getValue().getId() == id);
    }

    public UUID addToken(Info info){

        deleteUserTokens(info.getId());

        UUID token = UUID.randomUUID();
        tokens.put(token, info);

        return token;
    }

    public int getUserId(UUID token) throws TaskException {

        if (!tokens.containsKey(token)){
            throw new TaskException(INVALID_TOKEN);
        }

        return tokens.get(token).getId();
    }

    public ClientType getUserType(UUID token) throws TaskException {

        if (!tokens.containsKey(token)){
            throw new TaskException(INVALID_TOKEN);
        }

        return tokens.get(token).getClientType();
    }

    public boolean isAdmin(UUID token) throws TaskException {

        if (!tokens.containsKey(token)){
            throw new TaskException(INVALID_TOKEN);
        }

        if (!tokens.get(token).getClientType().equals(ClientType.ADMIN)){
            throw new TaskException(INVALID_TOKEN);
        }

        return true;

    }

    @Scheduled(fixedDelayString = "PT60S")
    public void deleteAfter30Min(){
        tokens.entrySet().removeIf(token -> token.getValue().getIssuedAt().plusMinutes(30).isBefore(LocalDateTime.now()));
    }

}
