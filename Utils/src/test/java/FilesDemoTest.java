import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FilesDemoTest {
    @Test
    public void getDirTest() throws Exception {
    }

    @Test
    public void openStream() throws Exception {
    }

    @Test
    public void fileOperation() throws Exception {
    }

    @Test
    public void findFiles() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(FilesDemo.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
