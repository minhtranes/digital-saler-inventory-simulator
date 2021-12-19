/*
 * Class: ResponseService
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
package vn.ds.study.dsi.app.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ResponseService {

    private Map<String, JsonNode> responsesMap = new ConcurrentHashMap<>();

    public JsonNode read(String responseId) {
        return responsesMap.get(responseId);
    }
    
    public JsonNode write(String responseId, JsonNode response) {
        return responsesMap.put(responseId, response);
    }
    
    public Stream<Entry<String, JsonNode>> responses(){
        return responsesMap.entrySet().stream();
    }
}
