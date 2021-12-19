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
package vn.ds.study.dsi.present.controller;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;
import vn.ds.study.dsi.app.service.ResponseService;

@Slf4j
@RestController
@RequestMapping("simulator")
public class SimulatorController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/request/{responseId}")
    public ObjectNode request(
        @PathVariable(name = "responseId", required = true) String responseId,
        @RequestParam(name = "responseTime", required = false, defaultValue = "PT1S") Duration responseTime) {
        long begin = System.currentTimeMillis();

        log.info(
            "Request for responseId = {} and responseTime = {}",
            responseId,
            responseTime);

        JsonNode jn = responseService.read(responseId);
        if (jn == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Unable to find resource");
        }
        ObjectNode res = (ObjectNode) jn;
        res.remove("registeredAt");

        long responseTimeMs = responseTime.toMillis();
        if (responseTimeMs > 1) {
            delay(responseTimeMs);
        }

        log.info("Request done in {}ms", (System.currentTimeMillis() - begin));
        return res;
    }

    private void delay(long delayTimeInMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(delayTimeInMs);
        }
        catch (InterruptedException e) {

        }
    }
}
