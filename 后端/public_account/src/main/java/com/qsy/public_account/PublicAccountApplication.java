package com.qsy.public_account;


import com.qsy.public_account.mapper.FirmInfomapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@MapperScan(basePackages = "com.qsy.public_account.mapper")
public class PublicAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicAccountApplication.class, args);
    }

}
