package com.github.gzaro.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static List<List<Object>> of(final List<Object> items) {
		final List<List<Object>> result = new ArrayList<>();
		for (int i = 1; i <= items.size(); i++) {
			result.addAll(of(items, i));
		}
		return result;
	}

	public static List<List<Object>> of(final List<Object> items, final int groupSize) {
		if (groupSize < 1 || items.size() < groupSize) {
			throw new IllegalArgumentException("group size cannot be less than 1 or greater than item count");
		}

		if (groupSize == 1) {
			return getElementsAsLists(items);
		} else {
			final List<List<Object>> result = new ArrayList<>();
			for (int i = 0; i < items.size(); i++) {
				final Object item = items.get(i);
				final List<List<Object>> subListPermutations = of(getSublistWithout(items, i), groupSize - 1);
				result.addAll(addItemToListItems(subListPermutations, item));
			}
			return result;
		}
	}

	private static List<List<Object>> getElementsAsLists(final List<Object> items) {
		final List<List<Object>> itemsLists = new ArrayList<>();
		for (final Object item : items) {
			final List<Object> itemList = new ArrayList<>();
			itemList.add(item);
			itemsLists.add(itemList);
		}
		return itemsLists;
	}

	private static List<Object> getSublistWithout(final List<Object> list, final int index) {
		final List<Object> newList = new ArrayList<>();
		newList.addAll(list.subList(0, index));
		newList.addAll(list.subList(index + 1, list.size()));
		return newList;
	}

	private static List<List<Object>> addItemToListItems(final List<List<Object>> list, final Object item) {
		final List<List<Object>> newList = new ArrayList<>();
		for (final List<Object> listItem : list) {
			final List<Object> newListItem = new ArrayList<>();
			newListItem.add(item);
			newListItem.addAll(listItem);
			newList.add(newListItem);
		}
		return newList;
	}

}