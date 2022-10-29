/*
 *
 */
package com.whitelabel.app.generic.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.whitelabel.app.generic.annotation.Format;
import com.whitelabel.app.generic.entity.GenericItem;
import com.whitelabel.app.generic.search.Params;

import lombok.Getter;

/**
 * Factory manage Json: entity and entity: Json conversion.
 */
public class FactoryConverter {

	/** The convert map. */
	private static Map<String, Params> convertMap = new HashMap<String, Params>();;

	/** The builder to string. */
	GsonBuilder builderToString;

	/** The builder to params search. */
	Gson builderToParamsSearch;

	@Getter
	ModelMapper modelMapper;

	/**
	 * Instantiates a new factory converter.
	 */
	@Inject
	public FactoryConverter() {
		builderToString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		builderToParamsSearch = new Gson();
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

	}

	/**
	 * Convert.
	 *
	 * @param params the params
	 * @return the string
	 */
	public String convert(Params params) {
		String fullText = "";
		if (params == null) {
			return fullText;
		}
		fullText = builderToString.create().toJson(params);
		return fullText;

	}

	/**
	 * Convert.
	 *
	 * @param params the params
	 * @return the params ES
	 */
	public Params convert(String params) {
		if (params == null) {
			return new Params();
		}

		if (convertMap.containsKey(params)) {
			return convertMap.get(params);
		} else {
			Params paramsSearch = builderToParamsSearch.fromJson(params, Params.class);
			if (paramsSearch == null) {
				paramsSearch = new Params();
			}
			JsonObject jsonObject = new JsonParser().parse(params).getAsJsonObject();

			if (jsonObject != null && jsonObject.get("fields") != null
					&& jsonObject.get("fields").getAsJsonObject() != null
					&& jsonObject.get("fields").getAsJsonObject().get("index") != null) {
				paramsSearch.getFields().put(GenericConstants.INDEX_ID,
						jsonObject.get("fields").getAsJsonObject().get("index").getAsString());
				paramsSearch.setClassType(FieldUtils.getClassFromClassName(
						jsonObject.get("fields").getAsJsonObject().get("index").getAsString(), GenericItem.class));
			}
			convertMap.put(params, paramsSearch);
			return paramsSearch;
		}
	}

	/**
	 * Convert to object.
	 *
	 * @param <T>       the generic type
	 * @param o         the o
	 * @param classType the class type
	 * @param nameField the name field
	 * @return the object
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public <T> Object convertToObject(Object o, Class<T> classType, Field nameField)
			throws InstantiationException, IllegalAccessException {
		if (o instanceof File) {
			return o;
		}
		if (classType.newInstance() instanceof Date) {
			String regexFormatter = nameField.getAnnotation(Format.class).formatter();
			SimpleDateFormat formatter = new SimpleDateFormat(regexFormatter);
			Date date = null;
			try {
				date = formatter.parse((String) o);
			} catch (ParseException e) {
				return new Date();
			}
			return date;
		}
		return o;
	}

}
