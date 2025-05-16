package io.github.hongweihao.ss08.ioc.resource.loader;


import io.github.hongweihao.ss08.ioc.resource.Resource;

public interface ResourceLoader {
    Resource getResource(String location);
}
