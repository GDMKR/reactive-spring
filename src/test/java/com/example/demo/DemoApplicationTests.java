//package com.example.demo;
//
//import com.example.demo.model.Post;
//import com.example.demo.repository.PostRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Collections;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class DemoApplicationTests {
//
//	@Autowired
//	private WebTestClient webTestClient;
//
//	@Autowired
//	PostRepository postRepository;
//
//	@Test
//	public void testCreatePost() {
//		Post post = new Post("This is a Test Post","");
//
//		webTestClient.post().uri("/posts")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8)
//				.body(Mono.just(post), Post.class)
//				.exchange()
//				.expectStatus().isOk()
//				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//				.expectBody()
//				.jsonPath("$.id").isNotEmpty()
//				.jsonPath("$.text").isEqualTo("This is a Test Post");
//	}
//
//	@Test
//	public void testGetAllPosts() {
//		webTestClient.get().uri("/posts")
//				.accept(MediaType.APPLICATION_JSON_UTF8)
//				.exchange()
//				.expectStatus().isOk()
//				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//				.expectBodyList(Post.class);
//	}
//
//	@Test
//	public void testGetSinglePost() {
//		Post post = postRepository.save(new Post("Hello, World!")).block();
//
//		webTestClient.get()
//				.uri("/postts/{id}", Collections.singletonMap("id", post.getId()))
//				.exchange()
//				.expectStatus().isOk()
//				.expectBody()
//				.consumeWith(response ->
//						Assertions.assertThat(response.getResponseBody()).isNotNull());
//	}
//
//	@Test
//	public void testUpdatePost() {
//		Post post = postRepository.save(new Post("Initial Post")).block();
//
//		Post newPostData = new Post("Updated Post");
//
//		webTestClient.put()
//				.uri("/posts/{id}", Collections.singletonMap("id", post.getId()))
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8)
//				.body(Mono.just(newPostData), Post.class)
//				.exchange()
//				.expectStatus().isOk()
//				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//				.expectBody()
//				.jsonPath("$.text").isEqualTo("Updated Post");
//	}
//
//	@Test
//	public void testDeletePost() {
//		Post post = postRepository.save(new Post("To be deleted")).block();
//
//		webTestClient.delete()
//				.uri("/posts/{id}", Collections.singletonMap("id",  post.getId()))
//				.exchange()
//				.expectStatus().isOk();
//	}
//}