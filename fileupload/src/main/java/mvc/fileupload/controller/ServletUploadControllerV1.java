package mvc.fileupload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    // upload-form으로 이동
    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }



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



        return "upload-form";
    }
}
