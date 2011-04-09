package test.com.beilers.testing;

import java.io.File;

public class UnitTestHelper {

    private final File personalTemporaryDirectory = new File(System.getProperty("java.io.tmpdir"),
                                                          System.getProperty("user.name"));

    protected File getPersonalTemporaryDirectory() {
        if (!personalTemporaryDirectory.exists()) {
            personalTemporaryDirectory.mkdirs();
        }
        return personalTemporaryDirectory;
    }
}
