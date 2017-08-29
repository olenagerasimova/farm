package com.zerocracy.pmo;

import com.zerocracy.Xocument;
import com.zerocracy.jstk.Item;
import com.zerocracy.jstk.Project;
import java.io.IOException;
import org.xembly.Directives;

/**
 * Speed of delivery.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.17
 * @todo #158:30m Add new stakeholder that should add a record to speed.xml
 *  on each 'Order was finished' claim. Also we need a voter which will
 *  pay attention to that numbers and vote for that person who is the fastest.
 */
public final class Speed {

    /**
     * Project.
     */
    private final Project pmo;

    /**
     * Login of the person.
     */
    private final String login;

    /**
     * Ctor.
     * @param pkt Project
     * @param user The user
     */
    public Speed(final Project pkt, final String user) {
        this.pmo = pkt;
        this.login = user;
    }

    /**
     * Bootstrap it.
     * @return This
     * @throws IOException If fails
     */
    public Speed bootstrap() throws IOException {
        try (final Item item = this.item()) {
            new Xocument(item.path()).bootstrap("pmo/speed");
        }
        return this;
    }

    /**
     * Add points to the list.
     * @param project A project
     * @param job A job
     * @param minutes How many minutes was spent on this job
     * @throws IOException If fails
     */
    public void add(final String project, final String job, final long minutes)
        throws IOException {
        try (final Item item = this.item()) {
            new Xocument(item.path()).modify(
                new Directives()
                    .xpath("/speed")
                    .add("order")
                    .attr("job", job)
                    .add("project")
                    .set(project)
                    .up()
                    .add("minutes")
                    .set(minutes)
                    .up()
                    .up()
            );
        }
    }

    /**
     * The item.
     * @return Item
     * @throws IOException If fails
     */
    private Item item() throws IOException {
        return this.pmo.acq(
            String.format("awards/%s.xml", this.login)
        );
    }
}
