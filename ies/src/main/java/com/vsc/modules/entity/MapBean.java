package com.vsc.modules.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>改写HashMap，实现不区分大小写的 String key Map</p>
 * @author 付翀
 * @version 2012-6-20
 */
public class MapBean<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 6013456580055888616L;

	/**
	 * <p>Override method, for lowcase key</p>
	 * @see java.util.HashMap#get(Object)
	 */
	@Override
	public V get(Object key) {
		Object key1 = key;
		if (key instanceof String) {
			key1 = ((String) key).toLowerCase();
		}
		return super.get(key1);
	}

	/**
	 * <p>Override method, for lowcase key</p>
	 * @see java.util.HashMap#put(Object, Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		K key1 = key;
		if (key instanceof String) {
			key1 = (K) ((String) key).toLowerCase();
		}
		return super.put(key1, value);
	}

	/**
	 * <p>Override method, for lowcase key</p>
	 * @see java.util.HashMap#putAll(Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Iterator<? extends Map.Entry<? extends K, ? extends V>> i = map.entrySet().iterator(); i.hasNext();) {
			Map.Entry<? extends K, ? extends V> e = i.next();
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public boolean containsKey(Object key) {
		Object key1 = key;
		if (key instanceof String) {
			key1 = ((String) key).toLowerCase();
		}
		return super.containsKey(key1);
	}

	@Override
	public V remove(Object key) {
		Object key1 = key;
		if (key instanceof String) {
			key1 = ((String) key).toLowerCase();
		}
		return super.remove(key1);
	}

}
