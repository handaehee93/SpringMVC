package mvc.fileupload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    // upload-form으로 이동
    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    // application.properties에 지정해 둔 파일 저장 경로를 가져온다.
    @Value("${file.dir}")
    private String fileDir;

    // post요청 처리하는 메서드
    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws
            ServletException, IOException {
        // request에 어떤 것이 들어오는지 출력
        log.info("request={}", request);

        // 상품명 input 태그의 값을 출력
        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        // multipart로 보내진 각각의 파트들을 출력
        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);


        // part의 값을 사용 해 보자.
        for (Part part : parts) {
            log.info("=====part=====");
            // part의 name을 가져온다.
            log.info("name={}",part.getName());

            // 각각의 part도 헤더와 바디로 구분되기에 그 헤더 값을 가져올 수 있다.
            Collection<String> partHeaderNames = part.getHeaderNames();
            for (String partHeaderName : partHeaderNames) {
                log.info("header {} {}", partHeaderName, part.getHeader(partHeaderName));
            }

            // 파일을 넘길시 해당 part의 헤더는 filename 이라는 정보가 추가되어 넘어온다. 그 값을 출력
            log.info("getSubmittedFileName {}", part.getSubmittedFileName());

            // part의 body 사이즈도 확인할 수 있다.
            log.info("size {}", part.getSize());

            // part의 body 데이터는 아래와 같이 읽을 수 있다.
            InputStream inputStream = part.getInputStream();
            String bodyData = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body {}", bodyData);

            // 파일 저장하기
            // 위의 로그를 확인하면 알겠지만 파일인 경우만 getSubmittedFileName에 값이 들어오기 때문에 아래 조건을 사용한것.
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("파일이 저장되는 fullPath {}",fullPath);
                part.write(fullPath);
            }

        }

        return "upload-form";
    }
}
