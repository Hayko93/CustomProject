package am.client.market.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private JsonUtil(){}

    private static  final ObjectMapper objMapper = new ObjectMapper();

    public static  String serialize(Object obj) throws JsonProcessingException{
        return objMapper.writer().writeValueAsString(obj);
    }
}
