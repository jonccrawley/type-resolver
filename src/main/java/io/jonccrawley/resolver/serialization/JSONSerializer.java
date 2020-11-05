package  io.jonccrawley.resolver.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JSONSerializer implements Serializer,Deserializer{

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00").create();

	@Override
	public <T> T deserialize(Class<T> clazz, String arg) {
		return GSON.fromJson(arg, clazz);
	}

	@Override
	public <T> T deserialize(Type type, String arg) {
		return GSON.fromJson(arg, type);
	}
	
	@Override
	public String serialize(Object arg) {
		if (arg == null) {
			return "{}";
		}

		return GSON.toJson(arg);
	}
}
