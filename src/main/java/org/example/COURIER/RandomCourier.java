package org.example.COURIER;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomCourier {
    public Courier generic() {
        return new Courier("caty", "Name", "1234");
    }
    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphanumeric(10),RandomStringUtils.randomAlphanumeric(6), RandomStringUtils.randomAlphanumeric(8));

    }
}
