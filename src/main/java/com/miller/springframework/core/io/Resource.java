package com.miller.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author miller
 * @version 1.0.0
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
