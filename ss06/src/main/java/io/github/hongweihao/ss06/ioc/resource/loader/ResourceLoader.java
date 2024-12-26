package io.github.hongweihao.ss06.ioc.resource.loader;


import io.github.hongweihao.ss06.ioc.resource.Resource;

public interface ResourceLoader {
    Resource getResource(String location);
}
