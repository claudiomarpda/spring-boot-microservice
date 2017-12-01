package com.example.microservice.service.post;

import com.example.microservice.service.account.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
@RequestMapping("rest/post")
public class PostServer {

    private Logger logger = Logger.getLogger(PostServer.class.getName());

    private List<Post> posts = new ArrayList<>();

    public PostServer() {
        posts.add(new Post(1, 1, "post description 1"));
        posts.add(new Post(2, 2, "post description 2"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Post post) {
        logger.info("created " + post.toString());
        posts.add(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post read(@PathVariable(value = "id") long id) {
        logger.info("read " + id);
        return posts.stream().filter(acc -> acc.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") long id, @RequestBody Post post) {
        logger.info("update " + id);
        posts.removeIf(p -> p.getId() == id);
        posts.add(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
        logger.info("delete " + id);
        posts.removeIf(p -> p.getId() == id);
    }

    public static void main(String[] args) {
        // Will configure using post-server.yml
        System.setProperty("spring.config.name", "post-server");
        SpringApplication.run(PostServer.class, args);
    }
}