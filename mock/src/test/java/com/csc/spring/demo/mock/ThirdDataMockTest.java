package com.csc.spring.demo.mock;

import com.csc.spring.demo.mock.bean.TestRepository;
import com.csc.spring.demo.mock.bean.TestService;
import com.csc.spring.demo.mock.bean.TestServiceImpl;
import com.csc.spring.demo.mock.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockApplication.class)

//@RunWith(MockitoJUnitRunner.Silent.class)
public class ThirdDataMockTest {

    @Test
    public void mockTest1(){
        User user = new User();
        user.setName("test1");
        user.setAge(1);
        TestService testService = mock(TestService.class);
        when(testService.getName(any())).thenReturn("test");
        String str = testService.getName(user);
        Assert.assertEquals(str, "test");
    }

//    @Autowired
//    private TestService testService;
//    @Test
//    public void mockTest2(){
//        User user = new User();
//        user.setName("test2");
//        user.setAge(1);
//        TestRepository testRepository = mock(TestRepository.class);
//        when(testRepository.getName(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals(str, "test");
//    }

//    @Mock
//    private TestService testService;
//    @Test
//    public void mockTest3(){
//        User user = new User();
//        user.setName("test3");
//        user.setAge(1);
//        when(testService.getName(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals(str, "test");
//    }

//    @Autowired
//    private TestService testService;
//    @Mock
//    private TestRepository testRepository;
//    @Test
//    public void mockTest4(){
//        User user = new User();
//        user.setName("test1");
//        user.setAge(1);
//        System.out.println(testRepository.hashCode());
//        when(testRepository.getName(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals(str, "test");
//    }

//    @Mock
//    private TestRepository testRepository;
//    @InjectMocks
//    @Autowired
//    private TestServiceImpl testServiceImpl;
//    @Test
//    public void mockTest4(){
//        User user = new User();
//        user.setName("test");
//        user.setAge(1);
//        System.out.println(testRepository.hashCode());
//        when(testRepository.getName(any())).thenReturn("test");
//        String str = testServiceImpl.getName(user);
//        Assert.assertEquals(str, "test");
//    }

//    @Spy
//    User user;
//    @Test
//    public void sumTest(){
//        Assert.assertEquals(3, user.sum(2, 1));
//    }

//    @Autowired
//    private TestService testService;
//    @MockBean
//    private TestRepository testRepository;
//    @Test
//    public void mockTest4(){
//        User user = new User();
//        user.setName("test4");
//        user.setAge(1);
//        System.out.println(testRepository.hashCode());
//        when(testRepository.getName(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals(str, "test");
//    }

//    @Autowired
//    private TestService testService;
//    @Qualifier("stringRedisTemplate")
//    @SpyBean
//    @MockBean 找不到对应的bean
//    private RedisTemplate redisTemplate;
//    @MockBean
//    private ValueOperations valueOperations;
//
//    @Test
//    public void redisTest() {
//        User user = new User();
//        user.setName("test1");
//        user.setAge(1);
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//        when(valueOperations.get(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals("test", str);
//    }

//
//    @Autowired
//    private TestService testService;
//    @MockBean
//    private StringRedisTemplate redisTemplate;
//    @MockBean
//    private ValueOperations valueOperations;
//
//    @Test
//    public void redisTest2() {
//        User user = new User();
//        user.setName("test1");
//        user.setAge(1);
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//        when(valueOperations.get(any())).thenReturn("test");
//        String str = testService.getName(user);
//        Assert.assertEquals("test", str);
//    }

    @InjectMocks
    private TestServiceImpl testService;
    @Mock
    private TestRepository testRepository;

    @Test
    public void transactional() {
        System.out.println(testRepository.hashCode());
        when(testRepository.getName(any())).thenReturn("test");
        String str = testService.transactional();
        System.out.println(str);
        Assert.assertEquals(str, "csc");
    }
}
