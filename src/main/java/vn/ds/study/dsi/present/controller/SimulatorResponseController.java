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
package vn.ds.study.dsi.present.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;
import vn.ds.study.dsi.app.service.ResponseService;

@Slf4j
@RestController
@RequestMapping("simulator/response")
public class SimulatorResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/register")
    ObjectNode registerResponse(@RequestBody ObjectNode registration) {

        String responseId = registration.findValue("responseId").textValue();
        ObjectNode responseValue = (ObjectNode) registration
            .findValue("responseValue");

        responseValue.put("registeredAt", LocalDateTime.now().toString());

        responseService.write(responseId, responseValue);
        log.info("Register the response with id {}", responseId);
        return responseValue;

    }

    @GetMapping("/list")
    ArrayNode listResponseShortInfo() {

        List<ObjectNode> infos = responseService.responses()
            .map(this::shortInfo).collect(Collectors.toList());
        log.info("List all the short info of the responses");
        return mapper.createArrayNode().addAll(infos);

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
