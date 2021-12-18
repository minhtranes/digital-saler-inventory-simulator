/*
 * Class: SimulatorController
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;
import vn.degitalsaler.inventory.app.service.ResponseService;

@Slf4j
@RestController
@RequestMapping("simulator")
public class SimulatorController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/request")
    public ObjectNode request(@RequestBody ObjectNode request) {
        String responseId = request.findValue("responseId").textValue();
        log.info("Request for response id {}", responseId);
        JsonNode jn = responseService.read(responseId);
        if (jn == null) {
            return null;
        }
        ObjectNode res = (ObjectNode) jn;
        res.remove("registeredAt");

        return res;
    }
}
