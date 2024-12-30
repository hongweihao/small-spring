package io.github.hongweihao.ss06.ioc.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * <p>
 * 根据 class path 读取配置文件内容
 * </p>
 *
 * @date 2022/11/16 13:36
 */
public class ResourceClassPath implements Resource {
    private final String name;
    private final ClassLoader classLoader;

    public ResourceClassPath(String name, ClassLoader classLoader) {
        Objects.requireNonNull(name);
        this.name = name;
        this.classLoader = classLoader;
    }

    @Override
    public InputStream getInputSteam() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(name);
        if (Objects.isNull(inputStream)){
            throw new FileNotFoundException("Not found this file: "+ name);
        }
        return inputStream;
    }
}
