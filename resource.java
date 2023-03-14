import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("example.txt");
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);
    }
}
