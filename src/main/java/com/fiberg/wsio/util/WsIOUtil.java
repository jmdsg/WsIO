package com.fiberg.wsio.util;

import com.google.common.collect.Multimap;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import static java.lang.String.format;

public final class WsIOUtil {

	private WsIOUtil() {  }

	public static final String NAME_SEPARATOR = ".";

	public static String addSuffixOf(String name, String suffix, String separator) {
		return StringUtils.isNotBlank(suffix) ? name + separator + suffix : name;
	}

	public static String addSuffix(String name, String suffix) {
		return addSuffixOf(name, suffix, StringUtils.EMPTY);
	}

	public static String addSuffixName(String name, String suffix) {
		return addSuffixOf(name, suffix, NAME_SEPARATOR);
	}

	public static String addPrefixOf(String name, String prefix, String separator) {
		return StringUtils.isNotBlank(prefix) ? prefix + separator + name : name;
	}

	public static String addPrefix(String name, String prefix) {
		return addPrefixOf(name, prefix, StringUtils.EMPTY);
	}

	public static String addPrefixName(String name, String prefix) {
		return addPrefixOf(name, prefix, NAME_SEPARATOR);
	}

	public static String addWrapOf(String name, String prefix, String suffix, String separator) {
		return addSuffixOf(addPrefixOf(name, prefix, separator), suffix, separator);
	}

	public static String addWrap(String name, String prefix, String suffix) {
		return addWrapOf(name, prefix, suffix, StringUtils.EMPTY);
	}

	public static String addWrapName(String name, String prefix, String suffix) {
		return addWrapOf(name, prefix, suffix, NAME_SEPARATOR);
	}

	public static <K, V> Set<K> inverseLookUps(Multimap<K, V> map, V value) {
		Set<K> keys = new HashSet<>();
		if (map.containsValue(value)) {
			for (Entry<K, V> entry : map.entries()) {
				if (entry.getValue() != null && entry.getValue().equals(value)) {
					keys.add(entry.getKey());
				}
			}
		}
		return keys;
	}

	public static <IFACE> IFACE forceDelegate(Object o,
	                                          Class<? extends IFACE> iface,
	                                          java.util.List<String> cadidates) {

		try {

			Class<?> clazz = o.getClass();
			while (clazz != null && clazz != Object.class) {

				for (String fullName : cadidates) {

					try {

						@SuppressWarnings("unchecked")
						IFACE delegate = (IFACE) Class.forName(fullName)
								.getConstructor(clazz)
								.newInstance(o);

						if (iface.isInstance(delegate)) {
							return delegate;
						}

					} catch (Exception e) {
						// nothing to do
					}

				}

				clazz = clazz.getSuperclass();

			}

		} catch (Throwable t) {
			// nothing to do return null
		}

		return null;

	}

}
