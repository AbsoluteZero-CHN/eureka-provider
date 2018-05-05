package cn.noload.eurekaprovider.modules.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caohao
 * @version 2018/4/24
 */
@RestController
@RequestMapping("/hello")
public class WelcomeRest {

    private final static Logger logger = LoggerFactory.getLogger(WelcomeRest.class);

    @Autowired
    private DiscoveryClient client;


    @GetMapping("/index")
    public String index() throws InterruptedException {
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                logger.info("host: " + instance.getHost() + " service_id: " + instance.getServiceId());
            });
        });

        /**
         * 为使断路器生效, 此处休眠 5 秒
         * Hystrix 默认超时时间为 2000 毫秒
         * */
        Thread.sleep(5000);

        return "Hello World";
    }
}
