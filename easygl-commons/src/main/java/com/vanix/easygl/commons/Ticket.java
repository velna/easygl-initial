package com.vanix.easygl.commons;

import com.vanix.easygl.commons.task.Task;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

@Slf4j
public class Ticket implements Identified<String> {

    public static final long TICKET_PER_SECOND = Ticket.ofSeconds(1);

    private static final List<Ticket> TS = new LinkedList<>();
    private static boolean shutdown;

    private final String id;
    private final BlockingQueue<Long> tickets = new LinkedBlockingQueue<>();
    private final Map<Long, LinkedList<Task>> tasks = new HashMap<>();
    private final LinkedList<Task> addtionTasks = new LinkedList<>();
    private long ticket = 0;
    private final ThreadLocal<ListIterator<Task>> gameIter = new ThreadLocal<>();

    static {
        init();
    }

    public static void shutdown() {
        shutdown = true;
    }

    private static void init() {
        new Thread(() -> {
            long t = 0;
            while (!shutdown) {
                try {
                    for (Ticket tkt : TS) {
                        tkt.tickets.offer(t);
                        if (tkt.tickets.size() * AppConfig.ticketIntervel > 2000 & t % TICKET_PER_SECOND == 0) {
                            log.warn("Ticket#{} lag {} tickets", tkt.id, tkt.tickets.size());
                        }
                    }
                    t++;
                    Thread.sleep(AppConfig.ticketIntervel);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "ticket").start();
    }

    public Ticket(String id) {
        this(id, runnable -> new Thread(() -> {
            while (true) {
                try {
                    if (runnable.applyAsInt(null) < 0) {
                        break;
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }, id + "-game").start());
    }

    public Ticket(String id, Consumer<ToIntFunction<List<Long>>> consumer) {
        this.id = id;
        TS.add(this);
        consumer.accept(this::doTieckt);
    }

    private void runTicket(long tk) {
        ticket = tk;
        List<Task> ts = tasks.remove(tk);
        if (ts != null) {
            runTasks(ts);
        }
    }

    private int doTieckt(List<Long> buf) {
        try {
            runTicket(tickets.take());
            if (buf != null && !tickets.isEmpty()) {
                tickets.drainTo(buf);
                for (Long tk : buf) {
                    runTicket(tk);
                }
                buf.clear();
            }
            runTasks(addtionTasks);
            return tasks.size();
        } catch (InterruptedException e) {
            return -1;
        }
    }

    @Override
    public String id() {
        return id;
    }

    private void runTasks(List<Task> ts) {
        while (!ts.isEmpty()) {
            ListIterator<Task> iter = ts.listIterator();
            gameIter.set(iter);
            while (iter.hasNext()) {
                Task task = iter.next();
                iter.remove();
                try {
                    task.run();
                    submit(task);
                } catch (Exception e) {
                    log.error("Error execute task: " + task, e);
                }
            }
        }
        gameIter.remove();
    }

    public void submit(Task task) {
        long next = task.next();
        if (next == 0) {
            ListIterator<Task> iter = gameIter.get();
            if (null == iter) {
                addtionTasks.add(task);
            } else {
                iter.add(task);
            }
        } else if (next > 0) {
            next += ticket;
            LinkedList<Task> ts = tasks.computeIfAbsent(next, k -> new LinkedList<>());
            ts.add(task);
        }
    }

    public long get() {
        return ticket;
    }

    public static long ofSeconds(double seconds) {
        return (long) (seconds * AppConfig.millPerSecond / AppConfig.ticketIntervel);
    }

    public static double toSeconds(long t) {
        return t * AppConfig.ticketIntervel / AppConfig.millPerSecond;
    }

    public When now() {
        return new When(get());
    }

    @EqualsAndHashCode
    @ToString
    public static class When {
        private final long ticket;
        private final long timestamp;

        private When(long ticket) {
            this.ticket = ticket;
            this.timestamp = System.currentTimeMillis();
        }

        public long getTicket() {
            return ticket;
        }

        public long getTimestamp() {
            return timestamp;
        }

    }
}
