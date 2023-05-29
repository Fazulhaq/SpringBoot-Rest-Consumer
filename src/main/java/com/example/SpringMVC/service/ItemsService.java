package com.example.SpringMVC.service;

import com.example.SpringMVC.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {

    @Autowired
    RestTemplate rst;

    public Items get(int itemId){
        Items items = rst.getForObject("http://localhost:8080/api/items/"+itemId, Items.class);
        return items;
    }

    public List<Items> getAll(){
        ResponseEntity<List<Items>> response = rst.exchange(
                "http://localhost:8080/api/items",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Items>>() {});

        List<Items> items = response.getBody();
        return items;
    }
    public Items update(Items items){
        return rst.postForObject("http://localhost:8080/api/items", items, Items.class);
    }
    public void delete(Items items){
        rst.delete("http://localhost:8080/api/items?id="+items.getItemId());
    }
}
