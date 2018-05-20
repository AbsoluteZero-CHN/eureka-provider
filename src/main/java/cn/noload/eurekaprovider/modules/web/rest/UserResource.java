package cn.noload.eurekaprovider.modules.web.rest;

import cn.noload.eurekaprovider.modules.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caohao
 * @version 2018/5/5
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    private final static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final Map<Long, User> USER_BEANS = new HashMap<Long, User>() {{
        put(1L, new User().id(1L).username("Linda").password("123456"));
        put(2L, new User().id(2L).username("Ada").password("111111"));
        put(3L, new User().id(3L).username("Bess").password("123321"));
    }};

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) throws InterruptedException {
        logger.info("getUser userId: " + id);
        return USER_BEANS.get(id);
    }

    @GetMapping("/batch/{ids}")
    public List<User> findAll(@PathVariable List<Long> ids) {
        logger.info("findAll userIds" + ids);
        List<User> resp = new ArrayList<>(ids.size());
        ids.forEach(id -> resp.add(USER_BEANS.get(id)));
        return resp;
    }
}
