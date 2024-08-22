package repository.entity;
import java.util.logging.Logger;
import java.util.logging.Level;
public class TrendEntity {
    private static final Logger logger = Logger.getLogger(TrendEntity.class.getName());
    private String trend;

    public String getTrend() {
        logger.log(Level.INFO, "Starting myMethod1");
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}
