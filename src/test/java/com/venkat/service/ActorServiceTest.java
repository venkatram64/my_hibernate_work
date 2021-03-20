package com.venkat.service;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ActorServiceTest {


    @Test
    public void getFilmFromStockTest(){
        ActorService service = new ActorService();
        Integer count = service.getFilmFromStock(1, 1);
        System.out.println("Count is " + count);
        Assertions.assertNull(count);
    }

}