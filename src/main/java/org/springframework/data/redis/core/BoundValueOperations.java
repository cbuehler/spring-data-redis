/*
 * Copyright 2011-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.redis.core;

import java.util.concurrent.TimeUnit;

import org.springframework.lang.Nullable;

/**
 * Value (or String in Redis terminology) operations bound to a certain key.
 *
 * @author Costin Leau
 * @author Mark Paluch
 * @author Jiahe Cai
 */
public interface BoundValueOperations<K, V> extends BoundKeyOperations<K> {

	/**
	 * Set {@code value} for the bound key.
	 *
	 * @param value must not be {@literal null}.
	 * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
	 */
	void set(V value);

	/**
	 * Set the {@code value} and expiration {@code timeout} for the bound key.
	 *
	 * @param value must not be {@literal null}.
	 * @param timeout
	 * @param unit must not be {@literal null}.
	 * @see <a href="http://redis.io/commands/setex">Redis Documentation: SETEX</a>
	 */
	void set(V value, long timeout, TimeUnit unit);

	/**
	 * Set the bound key to hold the string {@code value} if the bound key is absent.
	 *
	 * @param value must not be {@literal null}.
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
	 */
	@Nullable
	Boolean setIfAbsent(V value);

	/**
	 * Set the bound key to hold the string {@code value} and expiration {@code timeout} if the bound key is absent.
	 *
	 * @param value must not be {@literal null}.
	 * @param timeout
	 * @param unit must not be {@literal null}.
	 * @return {@literal null} when used in pipeline / transaction.
	 * @since 2.1
	 * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
	 */
	@Nullable
	Boolean setIfAbsent(V value, long timeout, TimeUnit unit);

	/**
	 * Get the value of the bound key.
	 *
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/get">Redis Documentation: GET</a>
	 */
	@Nullable
	V get();

	/**
	 * Set {@code value} of the bound key and return its old value.
	 *
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/getset">Redis Documentation: GETSET</a>
	 */
	@Nullable
	V getAndSet(V value);

	/**
	 * Increment an integer value stored as string value under the bound key by one.
	 *
	 * @param delta
	 * @return {@literal null} when used in pipeline / transaction.
	 * @since 2.1
	 * @see <a href="http://redis.io/commands/incr">Redis Documentation: INCR</a>
	 */
	@Nullable
	Long increment();

	/**
	 * Increment an integer value stored as string value under the bound key by {@code delta}.
	 *
	 * @param delta
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/incrby">Redis Documentation: INCRBY</a>
	 */
	@Nullable
	Long increment(long delta);

	/**
	 * Increment a floating point number value stored as string value under the bound key by {@code delta}.
	 *
	 * @param delta
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/incrbyfloat">Redis Documentation: INCRBYFLOAT</a>
	 */
	@Nullable
	Double increment(double delta);

	/**
	 * Decrement an integer value stored as string value under the bound key by one.
	 *
	 * @return {@literal null} when used in pipeline / transaction.
	 * @since 2.1
	 * @see <a href="http://redis.io/commands/decr">Redis Documentation: DECR</a>
	 */
	@Nullable
	Long decrement();

	/**
	 * Decrement an integer value stored as string value under the bound key by {@code delta}.
	 *
	 * @param delta
	 * @return {@literal null} when used in pipeline / transaction.
	 * @since 2.1
	 * @see <a href="http://redis.io/commands/decrby">Redis Documentation: DECRBY</a>
	 */
	@Nullable
	Long decrement(long delta);

	/**
	 * Append a {@code value} to the bound key.
	 *
	 * @param value must not be {@literal null}.
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/append">Redis Documentation: APPEND</a>
	 */
	@Nullable
	Integer append(String value);

	/**
	 * Get a substring of value of the bound key between {@code begin} and {@code end}.
	 *
	 * @param start
	 * @param end
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/getrange">Redis Documentation: GETRANGE</a>
	 */
	@Nullable
	String get(long start, long end);

	/**
	 * Overwrite parts of the bound key starting at the specified {@code offset} with given {@code value}.
	 *
	 * @param value must not be {@literal null}.
	 * @param offset
	 * @see <a href="http://redis.io/commands/setrange">Redis Documentation: SETRANGE</a>
	 */
	void set(V value, long offset);

	/**
	 * Get the length of the value stored at the bound key.
	 *
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see <a href="http://redis.io/commands/strlen">Redis Documentation: STRLEN</a>
	 */
	@Nullable
	Long size();

	/**
	 * @return never {@literal null}.
	 */
	RedisOperations<K, V> getOperations();
}
