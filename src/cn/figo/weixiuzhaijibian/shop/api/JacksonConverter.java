package cn.figo.weixiuzhaijibian.shop.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json数据解释器
 */
public class JacksonConverter implements Converter {
	private static final String MIME_TYPE = "application/json; charset=UTF-8";

	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public JacksonConverter() {

	}

	@Override
	public Object fromBody(TypedInput body, Type type)
			throws ConversionException {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructType(
					type);
			return objectMapper.readValue(body.in(), javaType);
		} catch (JsonParseException e) {
			throw new ConversionException(e);
		} catch (JsonMappingException e) {
			throw new ConversionException(e);
		} catch (IOException e) {
			throw new ConversionException(e);
		}
	}

	@Override
	public TypedOutput toBody(Object object) {
		try {
			String json = objectMapper.writeValueAsString(object);
			return new TypedByteArray(MIME_TYPE, json.getBytes("UTF-8"));
		} catch (JsonProcessingException e) {
			throw new AssertionError(e);
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}
}