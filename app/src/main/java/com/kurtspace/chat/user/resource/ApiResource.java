package com.kurtspace.chat.user.resource;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import lombok.AllArgsConstructor;
import lombok.Data;


@Path("/users")
public class ApiResource {

    private static final Map<Integer, User> users = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1000);
    
    @GET
    @Path("/ping")
    public String ping() {
        return "hello";
    }

    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {

        return users.values().stream()
            .sorted(Comparator.comparing(User::getName))
            .collect(Collectors.toList());
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void setUser(User user, @PathParam("id") int id) {
        user.setSub(id);
        users.put(id, user);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") int id) {
        return users.get(id);
    }

    @Path("/register")
    @POST
    @Produces("application/json")
    public Next register() {
        return new Next(nextId.getAndIncrement());
    }

    @Data
    @AllArgsConstructor
    private static final class Next {
        private int subject;
    }

    @Data
    private static final class User {

        private int sub;
        private String name;
        private String[] roles;
        private boolean active;
        private Boolean blocking;
        private Boolean blocked;

    }

}
