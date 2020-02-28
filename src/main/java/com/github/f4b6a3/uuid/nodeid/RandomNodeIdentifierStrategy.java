/*
 * MIT License
 * 
 * Copyright (c) 2018-2019 Fabio Lima
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.f4b6a3.uuid.nodeid;

import java.security.SecureRandom;
import java.util.Random;

import com.github.f4b6a3.uuid.util.NodeIdentifierUtil;
import com.github.f4b6a3.uuid.util.RandomUtil;

public class RandomNodeIdentifierStrategy implements NodeIdentifierStrategy {

	protected Random random;

	protected static final long NODEID_MAX = 0x0000FFFFFFFFFFFFL;

	public RandomNodeIdentifierStrategy() {
	}

	public RandomNodeIdentifierStrategy(Random random) {
		this.random = random;
	}

	/**
	 * Return a new random node identifier for every call.
	 * 
	 * It uses {@link SecureRandom} by default to generate 'cryptographic
	 * quality random number'. The first generated number is returned for all
	 * calls.
	 * 
	 * ### RFC-4122 - 4.1.6. Node
	 * 
	 * For systems with no IEEE address, a randomly or pseudo-randomly generated
	 * value may be used; see Section 4.5. The multicast bit must be set in such
	 * addresses, in order that they will never conflict with addresses obtained
	 * from network cards.
	 * 
	 * ### RFC-4122 - 4.5. Node IDs that Do Not Identify the Host
	 * 
	 * This section describes how to generate a version 1 UUID if an IEEE 802
	 * address is not available, or its use is not desired.
	 * 
	 * A better solution is to obtain a 47-bit cryptographic quality random
	 * number and use it as the low 47 bits of the node ID, with the least
	 * significant bit of the first octet of the node ID set to one. This bit is
	 * the unicast/multicast bit, which will never be set in IEEE 802 addresses
	 * obtained from network cards. Hence, there can never be a conflict between
	 * UUIDs generated by machines with and without network cards. (Recall that
	 * the IEEE 802 spec talks about transmission order, which is the opposite
	 * of the in-memory representation that is discussed in this document.)
	 * 
	 * @return a node identifier
	 */
	@Override
	public long getNodeIdentifier() {
		return getRandomNodeIdentifier() & NODEID_MAX;
	}

	/**
	 * Return a random generated node identifier.
	 * 
	 * {@link RandomNodeIdentifierStrategy#getNodeIdentifier()}
	 * 
	 * @return a random multicast node identifier
	 */
	protected long getRandomNodeIdentifier() {
		if (random == null) {
			return NodeIdentifierUtil.setMulticastNodeIdentifier(RandomUtil.getInstance().nextLong());
		}
		return NodeIdentifierUtil.setMulticastNodeIdentifier(random.nextLong());
	}
}
