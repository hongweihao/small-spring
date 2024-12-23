package pri.hongweihao.smallspring.ioc.resource.loader;

import pri.hongweihao.smallspring.ioc.resource.Resource;

public interface ResourceLoader {
    Resource getResource(String location);
}
