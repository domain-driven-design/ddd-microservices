package statusmachinetest;


import common.DemoEvent;
import common.DemoStatus;
import statusmachine.AbstractStatusMachine;

import java.util.Set;

public class DemoStatusMachine extends AbstractStatusMachine<DemoEvent, DemoStatus> {

    protected DemoStatusMachine() {
        super(DemoEvent.class, DemoStatus.class,
                builder ->
                        builder.addTransition(DemoEvent.SUBMIT, Set.of(DemoStatus.NEW, DemoStatus.UPDATED), DemoStatus.SUBMITTED)
                                .addTransition(DemoEvent.CALCULATE, DemoStatus.SUBMITTED, DemoStatus.CALCULATED)
                                .addTransition(DemoEvent.APPROVE, DemoStatus.CALCULATED, DemoStatus.APPROVED)
                                .addTransition(DemoEvent.REJECT, DemoStatus.SUBMITTED, DemoStatus.REJECTED)
        );
    }

}
