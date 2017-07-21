/**
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.pmo;

import com.zerocracy.jstk.fake.FkProject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link Awards}.
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.12
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class AwardsTest {

    @Test
    public void addsAndRemovesPoints() throws Exception {
        final Awards awards = new Awards(new FkProject(), "yegor").bootstrap();
        awards.add(1, "gh:test/test#1", "just for fun");
        awards.add(-1, "gh:test/test#2", "just for fun 2");
        awards.add(1, "gh:test/test#3", "just for fun 3");
        MatcherAssert.assertThat(awards.total(), Matchers.equalTo(1));
    }

    @Test
    public void iteratesPoints() throws Exception {
        final Awards awards = new Awards(new FkProject(), "jeff").bootstrap();
        awards.add(1, "gh:test/test#100", "some reason");
        awards.add(-1, "gh:test/test#10", "some other reason");
        MatcherAssert.assertThat(
            awards.iterate(), Matchers.iterableWithSize(2)
        );
    }

}