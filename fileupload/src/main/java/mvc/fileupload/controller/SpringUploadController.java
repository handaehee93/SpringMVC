package mvc.fileupload.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "/upload-form";
    }

    // spring을 사용하면 @RequestParam을 통해 HTML Form의 name의 값에 맞추어 값을 가져올 수 있다.
    // 파일인 경우는 MultipartFile로 가져오면 된다.
    @PostMapping("/upload")
    public String saveFile(
            @RequestParam String itemName,
            @RequestParam MultipartFile file,
            HttpServletRequest request
    ) throws IOException {
        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("MultiPartFile = {}",file);

        // getOriginalFilename을 통해 fullPath를 만들고, file.transferTo에 new File을 넣어 파일을 저장한다.
        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("fullPath = {}" , fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
}
