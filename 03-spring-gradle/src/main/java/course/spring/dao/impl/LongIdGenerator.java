package course.spring.dao.impl;

import course.spring.dao.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;


public class LongIdGenerator implements IdGenerator<Long> {
    private AtomicLong nextId = new AtomicLong(0L);
    public void setInitialValue(long initalVal) {
        nextId.set(initalVal);
    }
    @Override
    public Long getNextId() {
        return nextId.incrementAndGet();
    }
}
