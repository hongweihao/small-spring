package pri.hongweihao.smallspring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * TODO
 * </p>
 *
 * @date 2022/11/16 13:34
 */
public interface Resource {
    InputStream getInputSteam() throws IOException;
}
