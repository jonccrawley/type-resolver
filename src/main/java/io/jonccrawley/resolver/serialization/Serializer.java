package  io.jonccrawley.resolver.serialization;

import java.io.IOException;

public interface Serializer {

	String serialize(Object arg) throws IOException;

}
