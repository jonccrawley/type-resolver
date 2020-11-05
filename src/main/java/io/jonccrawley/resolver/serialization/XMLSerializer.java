package  io.jonccrawley.resolver.serialization;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.lang.reflect.Type;

public class XMLSerializer implements Serializer,Deserializer {
	
	private final XmlMapper mapper = new XmlMapper();
	private final TypeFactory typeFactory = TypeFactory.defaultInstance();
	
	@Override
	public <T> T deserialize(Class<T> clazz, String arg) throws IOException {
		return mapper.readValue(arg, clazz);
	}

	@Override
	public <T> T deserialize(Type type, String arg) throws IOException {
		return mapper.readValue(arg, typeFactory.constructType(type));
	}

	@Override
	public String serialize(Object arg) throws IOException {
		return mapper.writeValueAsString(arg);
	}
}
