package com.prprv.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yoooum
 */
@SpringBootTest
class UserServiceTests {
    String nameList = """
            博丽灵梦, 雾雨魔理沙, 爱丽丝·玛格特罗依德, 河城荷取, 明罗, 东风谷早苗, 八云紫, 西行寺幽幽子, 魂魄妖梦, 藤原妹红, 射命丸文,
            妹红, 坂田合欢, 三个面孔的妖怪, 鬼人正邪, 地灵殿的门卫, 稻荷山的兔子, 魔理沙的父母, 博丽爷爷, 小野塚小町, 宇佐见莲子,
            平安京的三位神子, 少名针妙丸, 土著民族的山蘑菇, 天狗的面食, 神绮, 梅蒂欣·克劳斯, 物部布都, 原野山脚的神社, 玉枝, 玄爷,
            露娜萨·普莉兹姆利巴, 蕾米莉亚·斯卡雷特, 芙兰朵露·斯卡雷特, 十六夜咲夜, 帕秋莉·诺蕾姬, 八坂神奈子, 洩矢诹访子, 琪露诺,
            爱塔妮缇丝, 铃瑚, 莉格露·奈芙莲, 玛艾露莎·希贝尔, 托露·卡妮娅, 安娜可丽雅·阿克巴, 苏我屠自古, 古明地觉, 红美玲, 克劳恩皮丝,
            丁礼田舞, 比那名居天子, 秦心, 天子的母亲, 天子的父亲, 空之境界·梦幻泡影·东方虹龙洞·千年幻想乡·东方天空璋·鬼形
            """;
    @Resource
    UserService userService;

    @Resource
    UserRepository userRepository;

    @Resource
    JsonMapper jsonMapper;
    @Test
    void contextLoads() {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, nameList.split(","));

        // 遍历名字列表，创建用户
        names.forEach(name -> {
            User user = new User();
            user.setUsername(name.trim());
            user.setPassword("123456");
            user.setEmail(name.trim() + "@prprv.com");
            // 暂停100毫秒，防止创建时间相同
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userRepository.save(user);
        });
    }

    @Test
    void page() {
        Pageable pageable = PageRequest.of(1, 5, Sort.Direction.fromString("desc"), "createTime");
        userService.findAll(pageable).forEach(user -> {
            try {
                System.out.println(jsonMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
