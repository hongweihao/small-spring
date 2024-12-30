package io.github.hongweihao.ss05.ioc.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * <p>
 * FileSystemResource
 * </p>
 *
 * @date 2022/11/16 13:36
 */
public class ResourceFileSystem implements Resource {
    private final String path;
    private final File file;

    public ResourceFileSystem(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputSteam() throws IOException {
        return Files.newInputStream(file.toPath());
    }

    public String getPath() {
        return path;
    }
}
