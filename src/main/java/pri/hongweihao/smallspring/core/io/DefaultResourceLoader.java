package pri.hongweihao.smallspring.core.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DefaultResourceLoader implements ResourceLoader {
    private final String CLASS_PATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        Objects.requireNonNull(location);

        if (location.startsWith(CLASS_PATH_PREFIX)) {
            String name = location.substring(CLASS_PATH_PREFIX.length());
            return new ClassPathResource(name, this.getClass().getClassLoader());
        }

        try {
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }
}
