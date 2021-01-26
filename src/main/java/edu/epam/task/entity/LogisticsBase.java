package edu.epam.task.entity;

import edu.epam.task.exception.TerminalException;
import edu.epam.task.util.VanProductStateComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsBase {

    private static final Logger logger = LogManager.getLogger();
    private static LogisticsBase INSTANCE;
    private static final int TERMINAL_CAPACITY = 4;
    private Queue<Terminal> terminals = new ArrayDeque<>();
    private Queue<Van> awayVans = new PriorityQueue<>(new VanProductStateComparator());
    private static Semaphore semaphore = new Semaphore(TERMINAL_CAPACITY, true);
    private static Lock lock = new ReentrantLock(true);

    private LogisticsBase() {
        for (int i = 0; i < TERMINAL_CAPACITY; i++) {
            terminals.add(new Terminal());
        }
    }

    public static LogisticsBase getInstance() {
        if (INSTANCE == null) {
            lock.lock();
            if (INSTANCE == null) {
                INSTANCE = new LogisticsBase();
                logger.info("Logistic base created by {}", Thread.currentThread().getName());
            }
            lock.unlock();
        }
        return INSTANCE;
    }

    public Terminal getTerminal(Van van) throws TerminalException {
        Terminal terminal;
        try {
            semaphore.acquire();
            lock.lock();
            terminal = terminals.poll();
            awayVans.remove(van);
            logger.info("Van -> {} in terminal -> {}", van, terminal);
        } catch (InterruptedException e) {
            logger.error("InterruptedException ->\n" + e);
            throw new TerminalException("Error in getting terminal");
        } finally {
            lock.unlock();
        }
        return terminal;
    }

    public void relieveTerminal(Van van, Terminal terminal) throws TerminalException {
        try {
            lock.lock();
            terminals.add(terminal);
            logger.info("Van -> {} relieve terminal -> {}", van, terminal);
        } finally {
            lock.unlock();
        }
    }

    public void moveVanToWait(Van van) {
        try {
            lock.lock();
            awayVans.add(van);
            logger.info("Van -> {} waiting for terminal", van);
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException ->\n" + e);
        } finally {
            lock.unlock();
        }
    }
}
