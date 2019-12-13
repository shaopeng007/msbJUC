package com.msb.han.juc.t05CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class T02_SyncVSAtomicIntegerVSLongAdder {

    Long Lcount=0L;

    AtomicInteger Acount =new AtomicInteger(0);

    LongAdder LAcount=new LongAdder();

    

}
