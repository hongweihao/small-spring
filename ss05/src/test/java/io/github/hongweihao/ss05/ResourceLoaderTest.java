package io.github.hongweihao.ss05;

import cn.hutool.core.io.IoUtil;
import io.github.hongweihao.ss05.ioc.resource.Resource;
import io.github.hongweihao.ss05.ioc.resource.loader.ResourceLoader;
import io.github.hongweihao.ss05.ioc.resource.loader.DefaultResourceLoader;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>
 * 资源加载测试
 * </p>
 *
 * @date 2022/12/12 13:39
 */
public class ResourceLoaderTest {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Test
    public void classpath_test() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        String s = IoUtil.readUtf8(resource.getInputSteam());
        System.out.println(s);
    }

    @Test
    public void file_test()throws IOException {
//        Resource resource = resourceLoader.getResource("D:\\code\\small-spring\\src\\test\\java\\resources\\important.properties");
//        Resource resource = resourceLoader.getResource("D:\\workspace\\small-spring\\src\\test\\java\\resources\\important.properties");
        Resource resource = resourceLoader.getResource("D:\\workspace\\small-spring\\ss05\\src\\test\\resources\\important.properties");
        String s = IoUtil.readUtf8(resource.getInputSteam());
        System.out.println(s);
    }

    @Test
    public void url_test() throws IOException {
        Resource resource = resourceLoader.getResource("https://raw.githubusercontent.com/fuzhengwei/small-spring/main/small-spring-step-06/src/test/resources/important.properties");
        String s = IoUtil.readUtf8(resource.getInputSteam());
        System.out.println(s);
    }
}
