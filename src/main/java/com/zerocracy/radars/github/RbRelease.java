/**
 * Copyright (c) 2016-2018 Zerocracy
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
package com.zerocracy.radars.github;

import com.jcabi.github.Github;
import com.zerocracy.Farm;
import com.zerocracy.pm.ClaimOut;
import javax.json.JsonObject;

/**
 * Release rebound.
 *
 * @author Carlos Miranda (miranda.cma@gmail.com)
 * @version $Id$
 * @since 0.26
 * @todo #1269:30min Each time a release is published, we should pay the
 *  architect a release bonus. Let's create a stakeholder that will react to
 *  the "Release was published" claim that will do this. See the following:
 *  https://github.com/zerocracy/farm/issues/1269#issuecomment-402054891
 *  http://www.zerocracy.com/policy.html#53
 */
public final class RbRelease implements Rebound {
    /**
     * Release JSON key.
     */
    private static final String JSON_KEY = "release";

    @Override
    public String react(final Farm farm, final Github github,
        final JsonObject event) {
        final String answer;
        if (event.containsKey(RbRelease.JSON_KEY)) {
            final JsonObject release = event.getJsonObject(RbRelease.JSON_KEY);
            final String tag = release.getString("tag_name");
            new ClaimOut()
                .type("Release was published")
                .param("tag", tag)
                .param("date", release.getString("published_at"));
            answer = String.format(
                "Release published: %d (tag: %s)",
                release.getInt("id"),
                tag
            );
        } else {
            answer = "Not a release event";
        }
        return answer;
    }
}
