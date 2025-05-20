package io.github.hongweihao.ss09.ioc.resource.loader;


import io.github.hongweihao.ss09.ioc.resource.Resource;

public interface ResourceLoader {
    Resource getResource(String location);
}
