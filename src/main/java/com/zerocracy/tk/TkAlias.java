/**
 * Copyright (c) 2016 Zerocracy
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
package com.zerocracy.tk;

import com.zerocracy.jstk.Farm;
import com.zerocracy.pmo.People;
import com.zerocracy.pmo.Pmo;
import java.io.IOException;
import java.util.logging.Level;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.auth.Identity;
import org.takes.facets.auth.RqAuth;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.RqHref;

/**
 * Add user alias.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TkAlias implements Take {

    /**
     * Farm.
     */
    private final Farm farm;

    /**
     * Ctor.
     * @param frm Farm
     */
    public TkAlias(final Farm frm) {
        this.farm = frm;
    }

    @Override
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    public Response act(final Request req) throws IOException {
        final Identity identity = new RqAuth(req).identity();
        if (identity.equals(Identity.ANONYMOUS)) {
            throw new RsForward(
                new RsFlash("You have to login first!", Level.SEVERE)
            );
        }
        final RqHref.Smart smart = new RqHref.Smart(new RqHref.Base(req));
        final String rel = smart.single("rel");
        final String href = smart.single("href");
        final People people = new People(new Pmo(this.farm)).bootstrap();
        if (people.find(rel, href).iterator().hasNext()) {
            throw new RsForward(
                new RsFlash("We've been already introduced, thanks!")
            );
        } else {
            people.link(
                identity.properties().get("login"),
                rel, href
            );
            throw new RsForward(
                new RsFlash(
                    String.format(
                        "Thanks, you've got an alias, @rel='%s', @href='%s'",
                        rel, href
                    )
                )
            );
        }
    }

}