//package com.stackroute.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.ProductServiceApplication;
//import com.stackroute.domain.Entity;
//import com.stackroute.domain.Review;
//import com.stackroute.exceptions.GlobalException;
//import com.stackroute.repository.EntityRepository;
//import com.stackroute.service.EntityService;
//import com.stackroute.service.EntityServiceImpl;
//import org.hamcrest.Matchers;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.times;
//
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
////@SpringBootTest(classes= ProductServiceApplication.class)
//public class EntityControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Entity entity;
//    private Review review;
//
//
//    @MockBean
//    private EntityService entityServiceImpl;
//
//    @InjectMocks
//    private EntityController entityController;
//
//
//    @Before
//    public void setUp() {
//        HashMap<String, String> values = new HashMap<String, String>();
//        values.put("naveen", "praksh");
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Date doj = new Date();
//        List<Review> list = new ArrayList<>();
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
//        //act
//        entity = new Entity();
//        entity.setAspectBasedRating(values);
//        entity.setEntityCategory("Restaurants");
//        entity.setEntityDescription("nice hotel");
//        entity.setEntityImageUrl("http://localhost//8080");
//        entity.setEntityName("kfc");
//        entity.setOverAllRating(9.0);
//        entity.setEntityPostedOn(doj);
//        entity.setEntityPostedBy("naveen");
//        entity.setReviews(list);
//    }
//
//    @After
//    public void tearDown() {
//        this.review = null;
//    }
//
//    @Test
//    public void givenAnEntityShouldReturnSavedEntity() throws Exception {
//
//
//        when(entityServiceImpl.saveEntity(any())).thenReturn(entity);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/entity")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(entity)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(entityServiceImpl, times(1)).saveEntity(any());
//    }
//
//    private static String asJsonString(final Object object) {
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//
//        } catch (Exception exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
////    @Test
////    public void givenMethodShouldReturnRecentEntities() throws Exception {
////        List<Entity> entityList = new ArrayList<>();
////        when(entityServiceImpl.getRecentEntities()).thenReturn(entityList);
////        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recent-entities")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(entity)))
////                .andExpect(MockMvcResultMatchers.status().isFound())
////                .andDo(MockMvcResultHandlers.print());
////        verify(entityServiceImpl, times(1)).getRecentEntities();
////
////    }
////
////    @Test
////    public void givenMethodShouldReturnTrendingEntities() throws Exception {
////        List<Entity> entityList = new ArrayList<>();
////        when(entityServiceImpl.getTrendingEntities()).thenReturn(entityList);
////        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trending-entities")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(entity)))
////                .andExpect(MockMvcResultMatchers.status().isFound())
////                .andDo(MockMvcResultHandlers.print());
////        verify(entityServiceImpl, times(1)).getTrendingEntities();
////
////    }
//
//    @Test
//    public void givenEntityNameShouldReturnEntity() throws Exception {
//        when(entityServiceImpl.getEntityByName(any())).thenReturn(entity);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/entity/phone")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(entity)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//        verify(entityServiceImpl, times(1)).getEntityByName((any()));
//    }
//}
//
//
//
//
