package api;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import entities.alarm.Alarm;
import wrappers.AlarmsWrapper;

public class AlarmResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;
    
    @Test
    public void testGetAlarms() {
        String respuesta = new RestBuilder<String>(URL).path(Uris.ALARMS).clazz(String.class).get().build();
        /*URI uri = UriComponentsBuilder.fromHttpUrl(URL + Uris.ALARMS).build().encode().toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String response = responseEntity.getBody();

        System.out.println("Response: " + response);*/
        System.out.println(respuesta);
    }
    
}
