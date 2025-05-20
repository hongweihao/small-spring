package io.github.hongweihao.ss09.ioc.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Resource
 * </p>
 *
 * @date 2022/11/16 13:34
 */
public interface Resource {
    InputStream getInputSteam() throws IOException;
}
