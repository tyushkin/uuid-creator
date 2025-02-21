package com.github.f4b6a3.uuid.util;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.UUID;

import org.junit.Test;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.util.CombUtil;

public class CombUtilTest {

	private static final int DEFAULT_LOOP_MAX = 1_000;

	@Test
	public void testGetPrefix() {
		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {

			long start = System.currentTimeMillis();
			UUID comb = UuidCreator.getPrefixComb();
			long end = System.currentTimeMillis();

			long prefix = CombUtil.getPrefix(comb);
			assertTrue("Wrong prefix", prefix >= start && prefix <= end);
		}
	}

	@Test
	public void testGetSuffix() {
		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {

			long start = System.currentTimeMillis();
			UUID comb = UuidCreator.getSuffixComb();
			long end = System.currentTimeMillis();

			long suffix = CombUtil.getSuffix(comb);
			assertTrue("Wrong suffix", suffix >= start && suffix <= end);
		}
	}

	@Test
	public void testGetPrefixInstant() {
		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {

			long start = System.currentTimeMillis();
			UUID comb = UuidCreator.getPrefixComb();
			long end = System.currentTimeMillis();

			Instant instant = CombUtil.getPrefixInstant(comb);
			assertTrue("Wrong prefix", instant.toEpochMilli() >= start && instant.toEpochMilli() <= end);
		}
	}

	@Test
	public void testGetSuffixInstant() {
		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {

			long start = System.currentTimeMillis();
			UUID comb = UuidCreator.getSuffixComb();
			long end = System.currentTimeMillis();

			Instant instant = CombUtil.getSuffixInstant(comb);
			assertTrue("Wrong prefix", instant.toEpochMilli() >= start && instant.toEpochMilli() <= end);
		}
	}
}
