package com.pedro.web.core.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponse {

    protected Object content;

    protected Map<JsonResponseType, List<String>> messages = new HashMap<>();

    public void setMessages(Map<JsonResponseType, List<String>> messages) {
        this.messages = messages;
    }


    public void setContent(Object content) {
        this.content = content;
    }
}
