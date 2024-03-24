package com.qsy.public_account;


import com.qsy.public_account.mapper.FirmInfomapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@MapperScan(basePackages = "com.qsy.public_account.mapper")
public class PublicAccountApplication {

    public static void main(String[] args) {
        System.out.println(" BBBBB   H   H    QQQ     K   K    L    ");
        System.out.println(" B    B  H   H   Q   Q    K  K     L    ");
        System.out.println(" BBBBB   HHHHH   Q  QQ    KK       L    ");
        System.out.println(" B    B  H   H   Q   Q    K  K     L    ");
        System.out.println(" BBBBB   H   H    QQQ Q   K   K    LLLL ");
        SpringApplication.run(PublicAccountApplication.class, args);
    }

}
