package com.asdc.funderbackend.serviceImpl;

import com.asdc.funderbackend.entity.Blog;
import com.asdc.funderbackend.repository.BlogRepo;
import com.asdc.funderbackend.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BlogServiceImplTest {

    @Mock
    private BlogRepo blogRepo;

    @Mock
    private UserRepo userRepo;

    private MockMvc mockMvc;

    @InjectMocks
    private BlogServiceImpl blogService;

    private static Long blog_id = 1L;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogRepo).build();
    }

    @Test
    public void getAllBlogsTest() throws Exception{
        List<Blog> mockBlogs = Arrays.asList(new Blog(),new Blog());
        when(blogRepo.findAll()).thenReturn(mockBlogs);
        List<Blog> actualBlogs = blogService.getAllBlogs();
        assertEquals(mockBlogs,actualBlogs);
    }

    @Test
    public void getAllBlogsEmptyTest() throws Exception{
        List<Blog> mockBlogs = Arrays.asList();
        when(blogRepo.findAll()).thenReturn(mockBlogs);
        List<Blog> actualBlogs = blogService.getAllBlogs();
        assertEquals(mockBlogs,actualBlogs);
    }

    @Test
    public void getAllBlogsWithNullTest() throws Exception{
        List<Blog> mockBlogs = Arrays.asList();
        when(blogRepo.findAll()).thenReturn(null);
        List<Blog> actualBlogs = blogService.getAllBlogs();
        assertNull(actualBlogs);
    }

    @Test
    public void getBlogByIdTest() throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date addDateBlog2 = df.parse("2023-09-05");
        Blog mockBlog = new Blog();
        mockBlog.setBlog_id(blog_id);
        mockBlog.setTitle("The Top 10 Crowdfunding Campaigns of 2022");
        mockBlog.setContent("Discover innovation at its peak with our roundup of the top 10 crowdfunding campaigns of 2022.");
        mockBlog.setImageName("https://rb.gy/l5r8m8");
        mockBlog.setShortDescription("Discover innovation at its peak with our roundup of the top 10 crowdfunding campaigns of 2022.");
        mockBlog.setAddedDate(addDateBlog2);
        when(blogRepo.findById(blog_id)).thenReturn(Optional.of(mockBlog));
        Blog actualBlog = blogService.getBlogById(blog_id);
        assertThat(actualBlog).isEqualTo(mockBlog);
    }

    @Test
    public void getBlogByIdNotNullTest() {
        Blog mockBlog = new Blog();
        when(blogRepo.findById(blog_id)).thenReturn(Optional.of(mockBlog));
        Blog actualBlog = blogService.getBlogById(blog_id);
        assertThat(actualBlog).isNotNull();
    }

    @Test
    public void getBlogByIdEmptyTest() {
        Blog mockBlog = new Blog();

        when(blogRepo.findById(blog_id)).thenReturn(Optional.of(mockBlog));

        Blog actualBlog = blogService.getBlogById(blog_id);

        assertThat(actualBlog).isEqualTo(mockBlog);
    }
}
