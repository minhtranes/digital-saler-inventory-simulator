/*
 * Class: SimulatorFunctions
 *
 * Created on Dec 18, 2021
 *
 * (c) Copyright Swiss Post Solutions Ltd, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package vn.degitalsaler.inventory.present.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SimulatorFunctions {

    private Map<String, JsonNode> responsesMap = new ConcurrentHashMap<>();

    Function<ObjectNode, ObjectNode> registerResponse() {
        return r -> {
            String responseId = r.findValue("responseId").textValue();
            ObjectNode responseValue = (ObjectNode) r
                .findValue("responseValue");

            responseValue.put("registeredAt", LocalDateTime.now().toString());

            responsesMap.put(responseId, responseValue);
            log.info("Register the response with id {}", responseId);
            return responseValue;
        };
    }

    Supplier<ArrayNode> listResponseShortInfo() {
        return () -> {
            List<ObjectNode> infos = responsesMap.entrySet().stream()
                .map(this::shortInfo).collect(Collectors.toList());
            log.info("List all the short info of the responses");
            return mapper.createArrayNode().addAll(infos);
        };
    }

    @Autowired
    private ObjectMapper mapper;

    private ObjectNode shortInfo(Entry<String, JsonNode> e) {
        ObjectNode info = mapper.createObjectNode();
        info.put("responseId", e.getKey());
        info.put(
            "registeredAt",
            e.getValue().findValue("registeredAt").textValue());
        return info;
    }

}
