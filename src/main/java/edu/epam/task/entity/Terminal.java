package edu.epam.task.entity;

import edu.epam.task.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Terminal {

    private static final Logger logger = LogManager.getLogger();
    private static final int WAIT_VAN_COUNT = 10;
    private String terminalId;

    public Terminal() {
        this.terminalId = IdGenerator.generateId();
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void handleVan(Van van) {
        if (van.getBootVan() == 0) {
            while (van.getBootVan() < van.getCapacity()) {
                van.boot(WAIT_VAN_COUNT);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("InterruptedException ->\n" + e);
                }
            }
            logger.info("Van -> {} was loaded to terminal -> {}", van, this.toString());
        } else {
            while (van.getBootVan() > 0) {
                van.relieve(WAIT_VAN_COUNT);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("InterruptedException ->\n" + e);
                }
            }
            logger.info("Van -> {} was relieved from terminal -> {}", van, this.toString());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("terminalId='").append(terminalId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
