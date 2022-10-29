package com.whitelabel.app.generic.others;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.StateTransitionException;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import com.whitelabel.app.generic.ui.table.CustomResultSet;
import com.whitelabel.app.generic.ui.table.RowId;
import com.whitelabel.app.generic.ui.table.RowItem;
import com.whitelabel.app.generic.utils.GenericConstants;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CacheHelper {

	private CacheManager cacheManager;

	public CacheHelper() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		try {
			cacheManager.init();
		} catch (StateTransitionException e) {
			log.error("CacheHelper:", e);
		}
		cacheManager.createCache(GenericConstants.CACHED_RESULT_KEY, CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, CustomResultSet.class, ResourcePoolsBuilder.heap(10)));
		cacheManager.createCache(GenericConstants.CACHED_ITEMS_KEY, CacheConfigurationBuilder
				.newCacheConfigurationBuilder(RowId.class, RowItem.class, ResourcePoolsBuilder.heap(10)));
	}

	public Boolean containsCachedResultsKey(String key) {
		Cache<String, CustomResultSet> cacheResults = cacheManager.getCache(GenericConstants.CACHED_RESULT_KEY,
				String.class, CustomResultSet.class);
		if (cacheResults != null) {
			return cacheResults.containsKey(key);
		}
		return false;
	}

	public CustomResultSet getCachedResults(String key) {
		Cache<String, CustomResultSet> cacheResults = cacheManager.getCache(GenericConstants.CACHED_RESULT_KEY,
				String.class, CustomResultSet.class);
		if (cacheResults.containsKey(key)) {
			return cacheResults.get(key);
		}
		return null;
	}

	public void putCachedResults(String key, CustomResultSet customResultSet) {
		Cache<String, CustomResultSet> cacheResults = cacheManager.getCache(GenericConstants.CACHED_RESULT_KEY,
				String.class, CustomResultSet.class);
		cacheResults.put(key, customResultSet);
	}

	public void removeCachedResults(String key) {
		Cache<String, CustomResultSet> cacheResults = cacheManager.getCache(GenericConstants.CACHED_RESULT_KEY,
				String.class, CustomResultSet.class);
		cacheResults.remove(key);
	}

	public void removeAllCachedResults() {
		Cache<String, CustomResultSet> cacheResults = cacheManager.getCache(GenericConstants.CACHED_RESULT_KEY,
				String.class, CustomResultSet.class);
		cacheResults.clear();
	}

	public Boolean containsItemsKey(RowId key) {
		Cache<RowId, RowItem> cacheResults = cacheManager.getCache(GenericConstants.CACHED_ITEMS_KEY, RowId.class,
				RowItem.class);
		if (cacheResults != null) {
			return cacheResults.containsKey(key);
		}
		return false;
	}

	public RowItem getCachedItems(RowId key) {
		Cache<RowId, RowItem> cachedItems = cacheManager.getCache(GenericConstants.CACHED_ITEMS_KEY, RowId.class,
				RowItem.class);
		if (cachedItems.containsKey(key)) {
			return cachedItems.get(key);
		}
		return null;
	}

	public void putCachedItems(RowId key, RowItem rowItem) {
		Cache<RowId, RowItem> cachedItems = cacheManager.getCache(GenericConstants.CACHED_ITEMS_KEY, RowId.class,
				RowItem.class);
		cachedItems.put(key, rowItem);
	}

	public void removeCachedItems(RowId key) {
		Cache<RowId, RowItem> cachedItems = cacheManager.getCache(GenericConstants.CACHED_ITEMS_KEY, RowId.class,
				RowItem.class);
		if (cachedItems.containsKey(key)) {
			cachedItems.remove(key);
		}
	}

	public void removeAllCachedItems() {
		Cache<RowId, RowItem> cachedItems = cacheManager.getCache(GenericConstants.CACHED_ITEMS_KEY, RowId.class,
				RowItem.class);
		cachedItems.clear();
	}
}
