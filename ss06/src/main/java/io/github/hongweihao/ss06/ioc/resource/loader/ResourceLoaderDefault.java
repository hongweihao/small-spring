package io.github.hongweihao.ss06.ioc.resource.loader;

import cn.hutool.core.util.ClassUtil;
import io.github.hongweihao.ss06.ioc.resource.Resource;
import io.github.hongweihao.ss06.ioc.resource.ResourceClassPath;
import io.github.hongweihao.ss06.ioc.resource.ResourceFileSystem;
import io.github.hongweihao.ss06.ioc.resource.ResourceUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ResourceLoaderDefault implements ResourceLoader {
    private static final String CLASS_PATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        Objects.requireNonNull(location);

        if (location.startsWith(CLASS_PATH_PREFIX)) {
            String name = location.substring(CLASS_PATH_PREFIX.length());
            return new ResourceClassPath(name, getClassLoader());
        }

        try {
            URL url = new URL(location);
            return new ResourceUrl(url);
        } catch (MalformedURLException e) {
            return new ResourceFileSystem(location);
        }
    }

    private ClassLoader getClassLoader() {
       /* ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }*/

        return ClassUtil.class.getClassLoader();
    }
}
