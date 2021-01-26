package edu.epam.task.entity;

import edu.epam.task.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Van extends Thread {

    private static final Logger logger = LogManager.getLogger();
    private String vanId;
    private int capacity;
    private ProductState productState;
    private int bootVan;

    //for boot
    public Van(int capacity, ProductState productState) {
        this.vanId = IdGenerator.generateId();
        this.capacity = capacity;
        this.productState = productState;
    }

    //for relieve
    public Van(int capacity, ProductState productState, int bootVan) {
        this.vanId = IdGenerator.generateId();
        this.capacity = capacity;
        this.productState = productState;
        this.bootVan = bootVan;
    }

    public String getVanId() {
        return vanId;
    }

    public void setVanId(String vanId) {
        vanId = vanId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
    }

    public int getBootVan() {
        return bootVan;
    }

    public void setBootVan(int bootVan) {
        this.bootVan = bootVan;
    }

    public void boot(int bootVan) {
        this.bootVan += bootVan;
    }

    public void relieve(int bootVan) {
        if (this.bootVan >= bootVan) {
            this.bootVan -= bootVan;
        } else {
            this.bootVan = 0;
        }
    }

    @Override
    public void run() {
        LogisticsBase logisticsBase = LogisticsBase.getInstance();

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Van{");
        sb.append("VanId='").append(vanId).append('\'');
        sb.append(", capacity=").append(capacity);
        sb.append(", productState=").append(productState);
        sb.append(", bootVan=").append(bootVan);
        sb.append('}');
        return sb.toString();
    }
}