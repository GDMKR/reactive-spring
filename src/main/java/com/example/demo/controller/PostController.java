package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public Flux<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/posts")
    public Mono<Post> createPosts(@RequestBody @Valid Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts/{id}")
    public Mono<ResponseEntity<Post>> getPostById(@PathVariable(value = "id") String postId) {
        return postRepository.findById(postId)
                .map(savedPost -> ResponseEntity.ok(savedPost))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/posts/{id}")
    public Mono<ResponseEntity<Post>> updatePost(@PathVariable(value = "id") String postId,
                                                  @RequestBody @Valid Post post) {
        return postRepository.findById(postId)
                .flatMap(existingPost -> {
                    existingPost.setText(post.getText());
                    return postRepository.save(existingPost);
                })
                .map(updatedPost -> new ResponseEntity<>(updatedPost, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/posts/{id}")
    public Mono<ResponseEntity<Void>> deletePost(@PathVariable(value = "id") String postId) {

        return postRepository.findById(postId)
                .flatMap(existingPost ->
                        postRepository.delete(existingPost)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // posts are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> streamAllposts() {
        return postRepository.findAll();
    }
}
