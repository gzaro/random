package com.github.gzaro.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	/**
	 * It arranges the given items in ordered lists of size 1 to item count (1-permutations of n, 2-permutations of
	 * n,...)
	 *
	 * @param items list of items to arrange
	 * @return list of 1 to item count sized arrangements
	 */
	public static List<List<Object>> of(final List<Object> items) {
		final List<List<Object>> result = new ArrayList<>();
		for (int i = 1; i <= items.size(); i++) {
			result.addAll(of(items, i));
		}
		return result;
	}

	/**
	 * It arranges the given items in ordered lists of given size (k-permutations of n)
	 *
	 * @param items list of items to arrange (n)
	 * @param groupSize size of ordered arrangements (k)
	 * @return list of k sized arrangements
	 */
	public static List<List<Object>> of(final List<Object> items, final int groupSize) {
		if (groupSize < 1 || items.size() < groupSize) {
			throw new IllegalArgumentException("group size cannot be less than 1 or greater than item count");
		}
		return getPermutations(items, groupSize);
	}

	private static List<List<Object>> getPermutations(final List<Object> items, final int groupSize) {
		if (groupSize == 1) {
			return getElementsAsLists(items);
		} else {
			final List<List<Object>> result = new ArrayList<>();
			for (int i = 0; i < items.size(); i++) {
				final Object item = items.get(i);
				final List<List<Object>> subListPermutations = getPermutations(getSublistWithout(items, i), groupSize - 1);
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