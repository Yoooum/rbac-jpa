package com.prprv.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    /**
     * 解决Jackson序列化LocalDateTime时报错，启用JSR310时间序列化。
     * @return JsonMapper
     */
    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.builder().addModule(new JavaTimeModule()).build();
    }
}
