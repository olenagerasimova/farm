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
package com.zerocracy.stk;

import com.jcabi.xml.XML;
import com.zerocracy.jstk.Project;
import com.zerocracy.jstk.Stakeholder;
import java.io.IOException;
import java.util.Arrays;
import org.xembly.Directive;
import org.xembly.Directives;

/**
 * Chain of stakeholders.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class StkChain implements Stakeholder {

    /**
     * Stakeholders.
     */
    private final Iterable<Stakeholder> list;

    /**
     * Ctor.
     * @param lst List of stakeholders
     */
    public StkChain(final Stakeholder... lst) {
        this(Arrays.asList(lst));
    }

    /**
     * Ctor.
     * @param lst List of stakeholders
     */
    public StkChain(final Iterable<Stakeholder> lst) {
        this.list = lst;
    }

    @Override
    public Iterable<Directive> process(final Project project,
        final XML xml) throws IOException {
        final Directives dirs = new Directives();
        for (final Stakeholder stk : this.list) {
            dirs.push().append(stk.process(project, xml)).pop();
        }
        return dirs;
    }

}