import org.example.beans.SomeBean;
import org.example.impl.InterfaceOneImpl;
import org.example.impl.InterfaceTwoImpl;
import org.example.injector.Injector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class InjectorTest {

    @Test
    void testInject() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("org.example.interfaces.InterfaceOne", "org.example.impl.InterfaceOneImpl");
        properties.setProperty("org.example.interfaces.InterfaceTwo", "org.example.impl.InterfaceTwoImpl");
        try (OutputStream output = new FileOutputStream("src/main/resources/application.properties")) {
            properties.store(output, null);
        }
        SomeBean component = new SomeBean();
        Injector.inject(component);
        assertNotNull(component.getFieldOne());
        assertTrue(component.getFieldOne() instanceof InterfaceOneImpl);
        assertNotNull(component.getFieldTwo());
        assertTrue(component.getFieldTwo() instanceof InterfaceTwoImpl);
    }

}
