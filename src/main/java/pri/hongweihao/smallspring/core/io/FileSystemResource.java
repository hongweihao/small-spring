package pri.hongweihao.smallspring.core.io;

import java.io.File;
import java.io.InputStream;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author Karl
 * @date 2022/11/16 13:36
 */
public class FileSystemResource implements Resource {

    private String path;
    private File file;



    @Override
    public InputStream getInputSteam() {
        return null;
    }
}
