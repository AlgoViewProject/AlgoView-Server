package AlgoView_Server.domain.news.controller;

import AlgoView_Server.domain.news.dto.NewsDto;
import AlgoView_Server.domain.news.service.NaverNewsSearchService;
import AlgoView_Server.domain.news.service.NaverNewsService;
import AlgoView_Server.global.analysis.Analysis;
import AlgoView_Server.global.analysis.dto.KeywordResponseDto;
import AlgoView_Server.global.analysis.repository.AnalysisJpaRepository;
import AlgoView_Server.global.analysis.service.KeywordService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class NaverNewsController {

    private final NaverNewsSearchService naverNewsSearchService;
    private final NaverNewsService naverNewsService;
    private final KeywordService keywordService;
    private final AnalysisJpaRepository analysisJpaRepository;

    @GetMapping("{analysisId}/news")
    public ResponseEntity<String> setNewsbyKeywords(@PathVariable("analysisId")Long analysisId) {
        log.info("analysisId : {}", analysisId );
        String clientId = "Pa5Sa3HtfQ9xTZuyRQWP"; //애플리케이션 클라이언트 아이디
        String clientSecret = "PRkum77Vx_"; //애플리케이션 클라이언트 시크릿


        String query = null;
        Integer display = 20;
        List<KeywordResponseDto> keywords = keywordService.getKeywords();
        for (KeywordResponseDto keywordByNews : keywords) {
            String keyword = keywordByNews.getKeyword();
            try {
                query = URLEncoder.encode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("검색어 인코딩 실패",e);
            }


            String apiURL = "https://openapi.naver.com/v1/search/news?query=" + query + "&display=" + display;    // JSON 결과


            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);
            String responseBody = naverNewsSearchService.get(apiURL,requestHeaders);
            List<NewsDto> newsDtoList = naverNewsService.parseNaverNewsJson(responseBody, keywordByNews);

            Analysis analysis = analysisJpaRepository.findById(analysisId)
                    .orElseThrow(() -> new EntityNotFoundException("Analysis not found with id: " + analysisId));
            for (NewsDto newsDto : newsDtoList) {
                newsDto.setKeyword(keywordByNews);
            }
            naverNewsService.saveNewsToDatabase(newsDtoList);
        }
        return ResponseEntity.ok("뉴스 데이터 저장 완료");
    }

    @GetMapping("{analysisId}/news/list")
    public List<NewsDto> getAllNews(@PathVariable("analysisId")Long id) {
        List<NewsDto> allNews = naverNewsService.getAllNews();
        return allNews;
    }
}
