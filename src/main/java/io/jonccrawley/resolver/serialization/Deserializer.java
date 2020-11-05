package  io.jonccrawley.resolver.serialization;

import java.io.IOException;
import java.lang.reflect.Type;

public interface Deserializer {

	<T> T deserialize(Class<T> clazz, String arg) throws IOException;

	<T> T deserialize(Type type, String arg) throws IOException;
}
