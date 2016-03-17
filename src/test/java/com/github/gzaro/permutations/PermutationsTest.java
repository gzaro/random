package com.github.gzaro.permutations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.gzaro.permutations.Permutations;

@SuppressWarnings("unchecked")
public class PermutationsTest {

	@Test(expected = IllegalArgumentException.class)
	public void getPermutationsOfSizeGreaterThanElementCountThrowsError() {
		Permutations.of(new ArrayList<>(), 1);
	}

	@Test
	public void emptyListReturnsEmptyList() {
		assertTrue(Permutations.of(new ArrayList<>()).size() == 0);
	}

	@Test
	public void oneItemListReturns1ItemList() throws Exception {
		this.verifyPermutations(Arrays.asList(this), Arrays.asList(this));
	}

	@Test
	public void twoItemsListReturnsTwo1ItemListsAndTwo2ItemsLists() throws Exception {
		this.verifyPermutations(Arrays.asList(1, 2), Arrays.asList(1), Arrays.asList(2), Arrays.asList(1, 2), Arrays.asList(2, 1));
	}

	@Test
	public void threeItemsListReturnsThree1ItemListsAndSix2ItemsListsAndSix3ItemsLists() throws Exception {
		this.verifyPermutations(Arrays.asList(1, 2, 3), Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(1, 2),
						Arrays.asList(1, 3), Arrays.asList(2, 1), Arrays.asList(2, 3), Arrays.asList(3, 1), Arrays.asList(3, 2),
						Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2),
						Arrays.asList(3, 2, 1));
	}

	@Test
	public void tenItemsListReturnsCorrectPermutationCount() throws Exception {
		final List<List<Object>> permutations = Permutations.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		assertEquals(9864100, permutations.size());
	}

	private void verifyPermutations(final List<Object> items, final List<Object>... expected) {
		final List<List<Object>> permutations = Permutations.of(items);
		assertEquals(Arrays.asList(expected), permutations);
	}

}
