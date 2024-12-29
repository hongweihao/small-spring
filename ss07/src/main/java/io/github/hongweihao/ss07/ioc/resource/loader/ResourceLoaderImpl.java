package io.github.hongweihao.ss07.ioc.resource.loader;

import cn.hutool.core.util.ClassUtil;
import io.github.hongweihao.ss07.ioc.resource.Resource;
import io.github.hongweihao.ss07.ioc.resource.ResourceClassPathImpl;
import io.github.hongweihao.ss07.ioc.resource.ResourceFileSystemImpl;
import io.github.hongweihao.ss07.ioc.resource.ResourceUrlImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ResourceLoaderImpl implements ResourceLoader {
    private static final String CLASS_PATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        Objects.requireNonNull(location);

        if (location.startsWith(CLASS_PATH_PREFIX)) {
            String name = location.substring(CLASS_PATH_PREFIX.length());
            return new ResourceClassPathImpl(name, getClassLoader());
        }

        try {
            URL url = new URL(location);
            return new ResourceUrlImpl(url);
        } catch (MalformedURLException e) {
            return new ResourceFileSystemImpl(location);
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
