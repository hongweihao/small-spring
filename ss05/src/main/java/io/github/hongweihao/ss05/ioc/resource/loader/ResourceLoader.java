package io.github.hongweihao.ss05.ioc.resource.loader;


import io.github.hongweihao.ss05.ioc.resource.Resource;

public interface ResourceLoader {
    Resource getResource(String location);
}
