package edu.epam.task.parser;

import edu.epam.task.entity.ProductState;
import edu.epam.task.entity.Van;
import edu.epam.task.entity.VanType;
import edu.epam.task.exception.ParseVanException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VanParser {

    private static final Logger logger = LogManager.getLogger();
    public static final String FOR_SPLIT_REGEX = "\\s";
    public static final int VAN_TYPE_INDEX = 3;
    public static final int VAN_CAPACITY_INDEX = 0;
    public static final int VAN_PRODUCT_STATE_INDEX = 1;
    public static final int VAN_COUNT_TO_BOOT_INDEX = 2;

    public Van parse(String line) throws ParseVanException {
        String[] vanInfo = line.split(FOR_SPLIT_REGEX);
        VanType vanType = VanType.valueOf(vanInfo[VAN_TYPE_INDEX]);
        int vanCapacity = Integer.parseInt(vanInfo[VAN_CAPACITY_INDEX]);
        ProductState productState = ProductState.valueOf(vanInfo[VAN_PRODUCT_STATE_INDEX]);
        Van van;
        if (vanType == VanType.TO_RELIEVE) {
            van = new Van(vanCapacity, productState);
        } else {
            int boot = Integer.parseInt(vanInfo[VAN_COUNT_TO_BOOT_INDEX]);
            van = new Van(vanCapacity, productState, boot);
        }
        logger.info("Van -> {} parsed correctly from line -> {}", van, line);
        return van;
    }
}
