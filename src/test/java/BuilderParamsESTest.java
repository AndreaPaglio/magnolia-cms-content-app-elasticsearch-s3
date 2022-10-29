import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import com.whitelabel.app.generic.custom.entity.Log;
import com.whitelabel.app.generic.search.BoostField;
import com.whitelabel.app.generic.search.GenericParamsBuilder;
import com.whitelabel.app.generic.search.Params;
import com.whitelabel.app.generic.search.TypeParam;

public class BuilderParamsESTest {

	@Test
	void createSearch() {
		GenericParamsBuilder builder = GenericParamsBuilder.createSearch();
		assertNotNull(builder);
	}

	@Test
	void createAdd() {
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class);
		assertNotNull(builder);
		assertNotNull(builder.get());
	}

	@Test
	void params() {
		Params paramsES = new Params();
		paramsES.setType(TypeParam.ADD);
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).params(paramsES);
		assertEquals(builder.get().getType(), TypeParam.ADD);
	}

	@Test
	void addField() {
		String field = "field";
		String value = "value";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addField(field, value);
		assertEquals(builder.get().getFields().get(field), value);
	}

	@Test
	void getField() {
		String field = "field";
		String value = "value";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addField(field, value);
		String valueMod = (String) builder.getField(field);
		assertEquals(value, valueMod);
	}

	@Test
	void removeField() {
		String field = "field";
		String value = "value";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addField(field, value);
		assertEquals(builder.get().getFields().get(field), value);
		builder.removeField(field, value);
		String valueMod = (String) builder.getField(field);
		assertNull(valueMod);
	}

	@Test
	void removeOrder() {
		String field = "field";
		String value = "value";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addOrder(field, value);
		assertEquals(builder.get().getOrders().get(field), value);
		builder.removeOrder(field, value);
		String valueMod = builder.getOrder(field);
		assertNull(valueMod);
	}

	@Test
	void addOrder() {
		String field = "field";
		String value = "value";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addOrder(field, value);
		assertEquals(builder.get().getOrders().get(field), value);
	}

	@Test
	void hightlight() {
		Boolean highlight = Boolean.TRUE;
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).hightlight(highlight);
		assertEquals(builder.get().getRelevanceSearch().getIsHighlight(), highlight);
	}

	@Test
	void get() {
		Params builder = GenericParamsBuilder.createAdd(Log.class).get();
		assertNotNull(builder);
	}

	@Test
	void sizePage() {
		int sizePage = 10;
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).sizePage(sizePage);
		assertEquals(builder.get().getSizePage(), sizePage);
	}

	@Test
	void size() {
		int size = 10;
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).size(size);
		assertEquals(builder.get().getSize(), size);
	}

	@Test
	void offset() {
		int offset = 10;
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).offset(offset);
		assertEquals(builder.get().getOffset(), offset);
	}

	@Test
	void fullTextSearch() {
		String fullTextSearch = "fullTextSearch";
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).fullTextSearch(fullTextSearch);
		assertEquals(builder.get().getRelevanceSearch().getFullTextSearch(), fullTextSearch);
	}

	@Test
	void simpleQuery() {
		Boolean isSimpleQuery = Boolean.TRUE;
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).simpleQuery(isSimpleQuery);
		assertEquals(builder.get().getRelevanceSearch().getIsSimpleQueryString(), isSimpleQuery);
	}

	@Test
	void addBoostField() {
		BoostField boostField = new BoostField();
		Integer boostInt = 10;
		boostField.setBoost(boostInt);
		GenericParamsBuilder builder = GenericParamsBuilder.createAdd(Log.class).addBoostField(boostField);
		assertEquals(builder.get().getRelevanceSearch().getBoostFields().size(), 1);
		assertEquals(builder.get().getRelevanceSearch().getBoostFields().get(0).getBoost(), boostInt);
	}

}
