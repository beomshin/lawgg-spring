package com.kr.lg.module.board;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.config.MockMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest(
        classes = LgWasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(MockMvcConfig.class) // Test config import 설정 (커스텀 mockMvc)
public class MyBoardFindTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @SpyBean
//    private BoardService boardService;
//
//    @SpyBean
//    private JwtDetailService jwtDetailService;
//
//    @BeforeEach
//    void setup() { // 유저 정보 세팅
//        UserDetails userDetails = jwtDetailService.loadUserByUsername("120"); // user 조회
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication); // security context authentication 주입
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("포지션 게시판 조회 테스트")
//    public void test() throws Exception {
//
//        FindMyBoardRequest request = FindMyBoardRequest.builder()
//                .page(0)
//                .pageNum(30)
//                .type(1)
//                .topic(0)
//                .build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = new ObjectMapper().convertValue(request, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//
//        mockMvc.perform(get("/api/v1/find/my/boards").queryParams(params))
//              .andExpect(status().isOk())
//              .andDo(print());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("포지션 게시판 파라미터 누락 테스트")
//    public void test2() throws Exception {
//
//        FindMyBoardRequest request = FindMyBoardRequest.builder()
////                .page(0)
//                .pageNum(30)
//                .type(5)
//                .topic(0)
//                .build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = new ObjectMapper().convertValue(request, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//
//        mockMvc.perform(get("/api/v1/find/my/boards").queryParams(params))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }
//
//
//    @Test
//    @Transactional
//    @DisplayName("포지션 게시판 파라미터 변조 테스트")
//    public void test3() throws Exception {
//
//        FindMyBoardRequest request = FindMyBoardRequest.builder()
//                .page(0)
//                .pageNum(30)
//                .type(5)
//                .topic(0)
//                .build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = new ObjectMapper().convertValue(request, new TypeReference<Map<String, String>>() {});
//        map.put("page", "변조");
//        params.setAll(map);
//
//        mockMvc.perform(get("/api/v1/find/my/boards").queryParams(params))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }
}
