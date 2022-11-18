package com.music.entrance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.music")
public class MusicEntranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicEntranceApplication.class, args);
    }

}
