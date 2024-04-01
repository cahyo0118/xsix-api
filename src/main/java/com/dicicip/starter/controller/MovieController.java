package com.dicicip.starter.controller;

import com.dicicip.starter.model.Movie;
import com.dicicip.starter.repository.MovieRepository;
import com.dicicip.starter.util.APIResponse;
import com.dicicip.starter.util.query.DB;
import com.dicicip.starter.util.query.QueryHelpers;
//import com.dicicip.starter.util.validator.Validator;
//import com.dicicip.starter.util.validator.ValidatorItem;

import com.dicicip.starter.util.validator.ValidatorItem;
import com.dicicip.vail.validations.Validator;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/Movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    DB db;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping(path = "test-validation", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public APIResponse<?> testValidation(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody HashMap requestBody) {

        // Validator<Movie> validator = new Validator<>(
        // requestBody,
        // new ValidatorItem("title", "required"),
        // new ValidatorItem("description", "required"),
        // new ValidatorItem("rating", "required"));

        Validator validator = new Validator(requestBody);
        validator.setValidation("name", "required", "max:25");
        validator.setValidation("description", "required");

        System.out.println("isValid --> " + validator.isValid());

        if (validator.isValid()) {
            requestBody.created_at = new Date();
            return new APIResponse<>(repository.save(requestBody));
        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    validator.getErrorsList(),
                    false,
                    "Failed save data");
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/send-message")
    public APIResponse<?> sendMessage(
            HttpServletRequest request,
            HttpServletResponse response) {

        try {
            System.out.println("START_SEND_MESSAGE ==> " + request.getParameter("message_description"));

            // send message synchronously
            // rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
            rocketMQTemplate.convertAndSend("1A0-s-88200049-01-0",
                    request.getParameter("message_description"));

            // System.out.println(
            // "message_callback --> " + rocketMQTemplate.sendAndReceive(
            // "1A0-s-88200049-01-0",
            // request.getParameter("message_description"),
            // String.class));

            System.out.println(
                    "message_callback --> " + rocketMQTemplate.sendAndReceive(
                            "1A0-s-88200049-01-0",
                            new Object(),
                            Object.class));
        } catch (Exception e) {
            System.out.println("MESSAGE_ERROR --> " + e.getMessage());
        }

        return new APIResponse<>(new HashMap());

    }

    @RequestMapping(method = RequestMethod.GET)
    public APIResponse<?> getAll(
            HttpServletRequest request,
            HttpServletResponse response) {
        return new APIResponse<>(QueryHelpers.getData(request.getParameterMap(), "movies", db));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public APIResponse<?> getOne(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response) {
        Optional<Movie> movie = this.repository.findById(id);

        if (movie.isPresent()) {
            return new APIResponse<>(movie.get());
        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    null,
                    false,
                    "Failed get data");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public APIResponse<?> store(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Movie requestBody) {

        Validator<Movie> validator = new Validator<>(
                requestBody,
                new ValidatorItem("title", "required"),
                new ValidatorItem("description", "required"),
                new ValidatorItem("rating", "required"));

        if (validator.valid()) {
            requestBody.created_at = new Date();
            return new APIResponse<>(repository.save(requestBody));
        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    validator.getErrorsList(),
                    false,
                    "Failed save data");
        }

    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public APIResponse<?> update(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Movie requestBody) {

        Validator<Movie> validator = new Validator<>(
                requestBody,
                new ValidatorItem("title", "required"),
                new ValidatorItem("description", "required"),
                new ValidatorItem("rating", "required"));

        if (validator.valid()) {

            Optional<Movie> movie = this.repository.findById(id);

            if (movie.isPresent()) {

                requestBody.id = movie.get().id;
                requestBody.created_at = movie.get().created_at;
                requestBody.updated_at = new Date();

                return new APIResponse<>(this.repository.save(requestBody));

            } else {
                response.setStatus(400);
                return new APIResponse<>(
                        null,
                        false,
                        "Failed get data");
            }

        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    validator.getErrorsList(),
                    false,
                    "Failed save data");
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public APIResponse<?> delete(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response) {
        Optional<Movie> movie = this.repository.findById(id);

        if (movie.isPresent()) {

            this.repository.delete(movie.get());

            return new APIResponse<>(null);

        } else {
            response.setStatus(400);
            return new APIResponse<>(
                    null,
                    false,
                    "Failed delete data");
        }
    }

}
